package com.example.portfoliocheker

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.portfoliocheker.ui.theme.Blue
import com.example.portfoliocheker.ui.theme.Orange
import com.example.portfoliocheker.ui.theme.White


@Composable
fun MainView(){
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 5.dp, end = 5.dp),

            ) {
            Button(onClick = { /*TODO*/ },
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
        TabRow(selectedTabIndex = 4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(text = "Название", modifier = Modifier.background(Orange).clickable { GoToPortfolio() })
            Text(text = "Стоимость", modifier = Modifier.background(Orange))
            Text(text = "Изменение", modifier = Modifier.background(Orange))
            Text(text = "Доходность", modifier = Modifier.background(Orange))
        }


    }


}
