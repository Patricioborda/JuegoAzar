package com.example.juegoazar.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HowToPlayScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¿Cómo Jugar?",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "1. Elige un número entre 1 y 5.\n" +
                    "2. Intenta adivinar el número que el sistema ha elegido.\n" +
                    "3. Si adivinas, ganas puntos. Si no, pierdes un intento.\n" +
                    "4. Tienes 5 intentos para adivinar el número correcto.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.popBackStack() }
        ) {
            Text("Volver al Inicio")
        }
    }
}
