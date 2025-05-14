package com.example.juegoazar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.juegoazar.screens.GameScreen
import com.example.juegoazar.screens.StartScreen

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object Game : Screen("game")
    // Podés agregar más pantallas así:
    // object HowToPlay : Screen("how_to_play")
}

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Start.route,
        modifier = modifier
    ) {
        composable(Screen.Start.route) {
            StartScreen(navController = navController)
        }
        composable(Screen.Game.route) {
            GameScreen()
        }
        // Ejemplo para futuras pantallas:
        // composable(Screen.HowToPlay.route) { HowToPlayScreen() }
    }
}
