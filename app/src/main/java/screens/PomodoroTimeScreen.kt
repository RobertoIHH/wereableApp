// app/src/main/java/com/simu_wear/app/screens/PomodoroTimerScreen.kt
package com.simu_wear.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.*
import kotlinx.coroutines.delay

@Composable
fun PomodoroTimerScreen(navController: NavController) {
    var isRunning by remember { mutableStateOf(false) }
    var timeRemaining by remember { mutableStateOf(25 * 60) } // 25 minutos en segundos
    var isBreak by remember { mutableStateOf(false) }
    var completedPomodoros by remember { mutableStateOf(0) }

    LaunchedEffect(isRunning) {
        while (isRunning && timeRemaining > 0) {
            delay(1000)
            timeRemaining--
        }

        if (timeRemaining == 0) {
            isRunning = false
            if (isBreak) {
                // Fin del descanso, volver a trabajo
                timeRemaining = 25 * 60
                isBreak = false
            } else {
                // Fin del pomodoro, empezar descanso
                completedPomodoros++
                timeRemaining = if (completedPomodoros % 4 == 0) 15 * 60 else 5 * 60 // Descanso largo cada 4
                isBreak = true
            }
        }
    }

    val minutes = timeRemaining / 60
    val seconds = timeRemaining % 60

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "⏱️ Pomodoro",
                    style = MaterialTheme.typography.title2,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Text(
                    text = if (isBreak) "☕ Descanso" else "🍅 Trabajando",
                    style = MaterialTheme.typography.body1,
                    color = if (isBreak) Color(0xFFFF9800) else Color(0xFF4CAF50),
                    textAlign = TextAlign.Center
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color(0xFF1E1E2E)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = String.format("%02d:%02d", minutes, seconds),
                            style = MaterialTheme.typography.title1,
                            color = Color.White
                        )

                        Text(
                            text = "Pomodoros: $completedPomodoros",
                            style = MaterialTheme.typography.caption1,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { isRunning = !isRunning },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (isRunning) Color.Red else Color(0xFF4CAF50)
                        )
                    ) {
                        Text(
                            text = if (isRunning) "⏸️" else "▶️",
                            color = Color.White
                        )
                    }

                    Button(
                        onClick = {
                            isRunning = false
                            timeRemaining = 25 * 60
                            isBreak = false
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                    ) {
                        Text("🔄", color = Color.White)
                    }
                }
            }

            item {
                Text(
                    text = "25 min trabajo + 5 min descanso\nDescanso largo cada 4 pomodoros",
                    style = MaterialTheme.typography.caption2,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                ) {
                    Text("← Volver", color = Color.White)
                }
            }
        }
    }
}