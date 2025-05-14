package com.example.juegoazar.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.platform.LocalContext
import com.example.juegoazar.ui.components.GuessInput
import com.example.juegoazar.ui.components.ActionButton
import androidx.compose.runtime.LaunchedEffect

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    var score by remember { mutableIntStateOf(0) }
    var bestScore by remember { mutableIntStateOf(0) }
    var userGuess by remember { mutableStateOf(TextFieldValue()) }
    var attempts by remember { mutableIntStateOf(0) }
    var numberToGuess by remember { mutableIntStateOf((1..5).random()) }
    var gameOver by remember { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(attempts) {
        if (attempts > 0) {
            showToast(context, "Intenta nuevamente. Intentos fallidos: $attempts")
        }
    }

    Column(
        modifier = modifier // <- corregido: se usa el modifier que viene como parámetro
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Puntaje: $score", style = MaterialTheme.typography.bodyLarge)
        Text("Mejor Puntaje: $bestScore", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(32.dp))

        GuessInput(
            value = userGuess,
            onValueChange = { newText -> userGuess = newText },
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ActionButton(
            onClick = {
                val guess = userGuess.text.toIntOrNull()
                if (guess != null && guess == numberToGuess) {
                    score += 10
                    if (score > bestScore) {
                        bestScore = score
                    }
                    numberToGuess = (1..5).random()
                    attempts = 0
                    userGuess = TextFieldValue("")
                } else {
                    attempts++
                    if (attempts >= 5) {
                        gameOver = true
                        score = 0
                        attempts = 0
                        numberToGuess = (1..5).random()
                        userGuess = TextFieldValue("")
                    }
                }
            },
            text = "Comprobar Adivinanza"
        )

        if (gameOver) {
            Text(
                "¡Juego terminado! Has fallado 5 veces.",
                color = MaterialTheme.colorScheme.error
            )
            Button(
                onClick = {
                    score = 0
                    attempts = 0
                    numberToGuess = (1..5).random()
                    gameOver = false
                    userGuess = TextFieldValue("")
                }
            ) {
                Text("Reiniciar Juego")
            }
        }
    }
}

fun showToast(context: android.content.Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}
