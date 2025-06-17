// app/src/main/java/com/simu_wear/app/screens/HealthScreen.kt
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

@Composable
fun HealthScreen(navController: NavController) {
    var waterCount by remember { mutableStateOf(0) }
    var exerciseMinutes by remember { mutableStateOf(0) }
    var stepsToday by remember { mutableStateOf(0) }

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "💚 Salud & Bienestar",
                    style = MaterialTheme.typography.title2,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            // Contador de agua
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color(0xFF2196F3).copy(alpha = 0.3f)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("💧 Hidratación", style = MaterialTheme.typography.body1)
                        Text("$waterCount / 8 vasos", style = MaterialTheme.typography.caption1)

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Button(
                                onClick = { if (waterCount > 0) waterCount-- },
                                modifier = Modifier.size(32.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                            ) {
                                Text("-", color = Color.White)
                            }

                            Button(
                                onClick = { if (waterCount < 8) waterCount++ },
                                modifier = Modifier.size(32.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                            ) {
                                Text("+", color = Color.White)
                            }
                        }
                    }
                }
            }

            // Ejercicio
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color(0xFF4CAF50).copy(alpha = 0.3f)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("🏃 Ejercicio", style = MaterialTheme.typography.body1)
                        Text("$exerciseMinutes min hoy", style = MaterialTheme.typography.caption1)

                        Button(
                            onClick = { exerciseMinutes += 15 },
                            modifier = Modifier.padding(top = 8.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
                        ) {
                            Text("+15 min", color = Color.White)
                        }
                    }
                }
            }

            // Recordatorios
            item {
                Button(
                    onClick = { navController.navigate("water_reminder") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF9800))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("⏰", style = MaterialTheme.typography.title2)
                        Text("Recordatorios", style = MaterialTheme.typography.body2)
                    }
                }
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