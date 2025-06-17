// app/src/main/java/com/simu_wear/app/screens/EntertainmentScreen.kt
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
fun EntertainmentScreen(navController: NavController) {
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
                    text = "🎮 Entretenimiento",
                    style = MaterialTheme.typography.title2,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                Button(
                    onClick = { navController.navigate("quote_game") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6B73FF))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("💭", style = MaterialTheme.typography.title2)
                        Text("Citas Inspiradoras", style = MaterialTheme.typography.body2)
                    }
                }
            }

            item {
                Button(
                    onClick = { navController.navigate("memory_game") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF9C27B0))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🧠", style = MaterialTheme.typography.title2)
                        Text("Juego de Memoria", style = MaterialTheme.typography.body2)
                    }
                }
            }

            item {
                Button(
                    onClick = { navController.navigate("puzzle_game") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00ACC1))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🧩", style = MaterialTheme.typography.title2)
                        Text("Puzzle Numérico", style = MaterialTheme.typography.body2)
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

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