package com.example.juegoazar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.juegoazar.screens.GameScreen
import com.example.juegoazar.screens.StartScreen
import com.example.juegoazar.screens.HowToPlayScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "start",
        modifier = modifier
    ) {
        composable("start") {
            StartScreen(navController)
        }
        composable("game") {
            GameScreen()
        }
        composable("howToPlay") {
            HowToPlayScreen(navController)
        }
    }
}
