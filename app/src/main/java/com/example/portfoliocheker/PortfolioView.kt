package com.example.portfoliocheker

import Portfolio
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.round

@Composable
fun PortfolioView(
    navController: NavController,
    portfolios: MutableList<Portfolio>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {

        ShowPortfolioNameAndImage(navController)


        val total = getTotalBalance(portfolios).toDouble()
        val invested = round( getTotalBalance(portfolios).toDouble() * 0.9)
        val profit = total - invested
        val profitColor = if (profit >= 0) Color.Green else Color.Red

        ShowPortfolioInfo(invested, total, profit, profitColor)
        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Shares",
                modifier = Modifier.clickable { DeleteAllPortfolio(portfolios,navController)}
            )

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Shares",
                modifier = Modifier.clickable {
                    navController.navigate("Shares")
                }
            )

        }

        ShowBlocks(portfolios,navController)



    }
}

@Composable
fun ShowPortfolioNameAndImage(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Left
    ) {

        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Portfolio",
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .size(150.dp)
                .border(
                    BorderStroke(4.dp, Color.Green),
                    RectangleShape
                )
                .padding(4.dp)
                .clip(RectangleShape)
                .clickable { navController.navigate("Main") }
        )
        Text(text = "Portfolio",fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(50.dp)
            )


    }
}

@Composable
fun ShowPortfolioInfo(invested: Double, total: Double, profit: Double, profitColor: Color) {
    Box(
        modifier = Modifier
            .border(
                BorderStroke(2.dp, Color.Green),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(10.dp)
    ){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            StockStat("Инвестированно", invested)
            StockStat("Всего", total)
            StockStat("Профит", profit, profitColor, showSign = true)

        }
    }
    }
}
@Composable
fun StockStat(label: String, value: Double, color: Color? = null, showSign: Boolean = false) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label
            )
            Text(
                text = "${if (showSign) if (value >= 0) "+" else "" else ""}${value}",
                color = color ?: MaterialTheme.colorScheme.onSurface

            )
        }
}
@Composable
fun ShowBlocks(portfolios: MutableList<Portfolio>, navController: NavController) {
    //Spacer(modifier = Modifier.height(8.dp))

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(portfolios) { block ->
            BlockToCompose(block,navController)
        }
    }
}

@Composable
fun BlockToCompose(block: Portfolio,navController: NavController) {

    Box(
        modifier = Modifier
            .border(
                BorderStroke(2.dp, Color.Green),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(10.dp)
            .clickable {
                navController.navigate("Shares")
            }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                    Text("Акция:${block.name}")
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text("Количество:${block.count}")
                        Text("Цена:${block.price}")
                    }


            }
        }
    }
}

fun DeleteAllPortfolio(portfolios: MutableList<Portfolio>,navController: NavController){
    portfolios.clear()
    println("очистил$portfolios")
    navController.navigate("Portfolio")
}