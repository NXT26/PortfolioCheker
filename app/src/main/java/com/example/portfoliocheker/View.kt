package com.example.portfoliocheker

import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.portfoliocheker.ui.theme.Blue
import com.example.portfoliocheker.ui.theme.Orange
import com.example.portfoliocheker.ui.theme.White
import kotlin.system.exitProcess


@Composable
fun MainView(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileImage()
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Баланс портфеля: ${getTotalBalance()}")
        Spacer(modifier = Modifier.height(16.dp))

        GoMainScreenButton(navController)
        Spacer(modifier = Modifier.weight(1f))

    }


}

fun getTotalBalance(): Int {
    return 100
}
@Composable
fun ProfileImage() {
    val borderWidth = 4.dp
    Image(
        painter = painterResource(R.drawable.img),
        contentDescription = "Portfolio",

        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, androidx.compose.ui.graphics.Color.Green),
                RectangleShape
            )
            .padding(borderWidth)
            .clip(RectangleShape)
    )
}

@Composable
fun GoMainScreenButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("Portfolio") },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = "Перейти в портфель")
    }
}

