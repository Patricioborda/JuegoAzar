package com.example.juegoazar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.juegoazar.screens.BestScoreScreen
import com.example.juegoazar.screens.GameScreen
import com.example.juegoazar.screens.StartScreen
import com.example.juegoazar.screens.HowToPlayScreen
import com.example.juegoazar.screens.GameOverScreen  // Asegúrate de importar GameOverScreen

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
            GameScreen(navController = navController) // Asegúrate de pasar el navController
        }
        composable("howToPlay") {
            HowToPlayScreen(navController)
        }
        composable("bestScore") {
            BestScoreScreen(onBack = { navController.popBackStack() })
        }
        composable("game_over") {
            GameOverScreen(navController = navController) // Asegúrate de pasar el navController
        }
    }
}
