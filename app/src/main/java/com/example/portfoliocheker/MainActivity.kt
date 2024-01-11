package com.example.portfoliocheker

import Portfolio
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var portfolios = mutableListOf<Portfolio>()
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Main") {
                composable("Main") {MainView(navController,portfolios) }
                composable("Portfolio") { PortfolioView(navController,portfolios) }
                composable("Shares") { AddSharesView("","","",navController, portfolios) }

            }

            }
        }

}







