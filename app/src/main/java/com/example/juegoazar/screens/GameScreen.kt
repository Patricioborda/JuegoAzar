package com.example.juegoazar.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.juegoazar.ui.components.GuessInput
import com.example.juegoazar.ui.components.ActionButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun GameScreen(navController: NavController, modifier: Modifier = Modifier) {
    var score by remember { mutableIntStateOf(0) }
    var bestScore by remember { mutableIntStateOf(0) }
    var userGuess by remember { mutableStateOf(TextFieldValue()) }
    var attempts by remember { mutableIntStateOf(0) }
    var numberToGuess by remember { mutableIntStateOf((1..5).random()) }
    var gameOver by remember { mutableStateOf(false) }  // Estado para controlar si el juego terminó

    val context = LocalContext.current
    val dataStore = remember { com.example.juegoazar.data.DataStoreManager(context) }
    val scope = rememberCoroutineScope()

    // Leer mejor puntaje al iniciar
    LaunchedEffect(Unit) {
        scope.launch {
            dataStore.maxScore.collectLatest { saved ->
                bestScore = saved
            }
        }
    }

    // Mostrar toast en cada intento fallido
    LaunchedEffect(attempts) {
        if (attempts > 0) {
            showToast(context, "Intenta nuevamente. Intentos fallidos: $attempts")
        }
    }

    // Evitar que se navegue si el juego ya está terminado
    if (!gameOver) {
        Column(
            modifier = modifier
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
                            scope.launch {
                                dataStore.saveMaxScore(score)
                            }
                        }
                        numberToGuess = (1..5).random()
                        attempts = 0
                        userGuess = TextFieldValue("")
                    } else {
                        attempts++
                        if (attempts >= 5 && !gameOver) {  // Protege contra la navegación repetida
                            // Al llegar a 5 intentos fallidos, actualizar el estado de gameOver
                            gameOver = true
                            // Se navega a la pantalla de "game_over"
                            navController.navigate("game_over")
                        }
                    }
                },
                text = "Comprobar Adivinanza"
            )
        }
    } else {
        // Si el juego está terminado, se evita cualquier acción adicional
        Text("Juego Terminado!")
    }
}

fun showToast(context: android.content.Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

