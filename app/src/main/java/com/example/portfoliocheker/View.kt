package com.example.portfoliocheker

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.portfoliocheker.ui.theme.Blue
import com.example.portfoliocheker.ui.theme.Orange
import com.example.portfoliocheker.ui.theme.White
data class Portfolio(
    val name: String,
    val price: Double,
    val change: Double,
    val income: Double
)

@Composable
fun MainView(){
    var portfolios by remember { mutableStateOf<List<Portfolio>>(emptyList()) }
    var newPortfolioName by remember { mutableStateOf("") }
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 5.dp, end = 5.dp),

            ) {
            Button(onClick = {
                if (newPortfolioName.isNotBlank()) {
                    portfolios = portfolios + Portfolio(
                        name = newPortfolioName,
                        price = 0.0,
                        change = 0.0,
                        income = 0.0
                    )
                    newPortfolioName = ""}
            },
                modifier = Modifier.fillMaxWidth(0.5f)) {

                Text(text = "Добавить портфель")

            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()) {
//            Icon(painter = R.drawable.ic_add_foreground, contentDescription = "icon")

                Text(text = "Добавить бумагу")

            }
        }
        TabRow(selectedTabIndex = 4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)

            ) {
            Text(text = "Название", modifier = Modifier.background(Orange), color = White)
            Text(text = "Стоимость", modifier = Modifier.background(Orange))
            Text(text = "Изменение", modifier = Modifier.background(Orange))
            Text(text = "Доходность", modifier = Modifier.background(Orange))
        }
        LazyRow {
            items(portfolios) { portfolio ->
                PortfolioCard(portfolio = portfolio)
            }
        }
    }

}
@Composable
fun PortfolioCard(portfolio: Portfolio) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp, 200.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = portfolio.name, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Стоимость: ${portfolio.price}", textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Изменение: ${portfolio.change}", textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Доход: ${portfolio.income}", textAlign = TextAlign.Center)
        }
    }
}

