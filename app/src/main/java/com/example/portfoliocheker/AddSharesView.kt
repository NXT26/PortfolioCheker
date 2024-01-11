package com.example.portfoliocheker

import Portfolio
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddSharesView(
    SharesName: String? = null,
    SharesPrice: String? = null,
    SharesQuantity: String? = null,

    navController: NavController,
    portfolios: MutableList<Portfolio>,
){
    var SharesName by remember { mutableStateOf(SharesName) }
    var SharesPrice by remember { mutableStateOf(SharesPrice) }
    var SharesQuantity by remember { mutableStateOf(SharesQuantity) }

    var nameIsValid by remember { mutableStateOf(true) }
    var priceIsValid by remember { mutableStateOf(true) }
    var quantityIsValid by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = SharesName ?: "",
            onValueChange = {
                SharesName = it
                nameIsValid =  ((SharesName?.length ?: 0) < 15)
            },
            placeholder = {
                Text("TSLA")
            },
            colors = TextFieldDefaults.colors(if (nameIsValid) Color.Black else Color.Red),
            label = { Text("Имя акции") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )


        // TextField для ввода цены акции
        TextField(
            value = SharesPrice.toString(),
            onValueChange = { it ->
                SharesPrice = it

                priceIsValid = try {
                    SharesPrice?.toDouble()
                    true
                } catch (e: NumberFormatException) {
                    false
                }
            },
            colors = TextFieldDefaults.colors(if (priceIsValid) Color.Black else Color.Red),
            label = { Text("Цена акции") },
            placeholder = {
                Text(
                    "100.00",
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = SharesQuantity.toString(),
            onValueChange = {
                SharesQuantity = it

                quantityIsValid = try {
                    SharesQuantity?.toInt()
                    true
                } catch (e: NumberFormatException) {
                    false
                }
            },
            label = { Text("Количество акций") },
            placeholder = {
                Text(
                    "10",
                )
            },
            colors = TextFieldDefaults.colors(if (quantityIsValid) Color.Black else Color.Red),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Button(
            onClick = {
                if (nameIsValid && priceIsValid && quantityIsValid) {
                    insertInvestment(SharesName, SharesPrice, SharesQuantity, portfolios)

                    navController.navigate("Portfolio")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Подтвердить")
        }

        Button(
            onClick = {
                onTradeDelete()
                navController.navigate("Portfolio")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Удалить")
        }

        Button(
            onClick = {
                onTradeCancel()
                navController.navigate("Portfolio")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Отмена")
        }
    }

    }
fun insertInvestment(
    SharesName: String?,
    SharesPrice: String?,
    SharesQuantity: String?,
    portfolios:MutableList<Portfolio>
) {
    val portfolio = Portfolio(
        name = SharesName.toString(),
        price = SharesPrice!!.toDouble(),
        count = SharesQuantity!!.toInt(),
    )
    portfolios.add(portfolio)
}

fun onTradeDelete(): String {

    return "deleted"
}

fun onTradeCancel(): String {
    return "canceled"
}






