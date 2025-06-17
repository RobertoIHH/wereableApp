// app/src/main/java/com/simu_wear/app/screens/UtilitiesScreen.kt
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
import java.util.*

@Composable
fun UtilitiesScreen(navController: NavController) {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val timeBasedAlert = when (currentHour) {
        in 6..11 -> "🌅 Buenos días! Hora de hidratarse"
        in 12..17 -> "☀️ Tarde productiva! Toma un descanso"
        in 18..21 -> "🌆 Noche perfecta para relajarse"
        else -> "🌙 Es hora de descansar"
    }

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
                    text = "⚡ Utilidades",
                    style = MaterialTheme.typography.title2,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            // Alerta contextual por tiempo
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color(0xFFFF9800).copy(alpha = 0.3f)
                ) {
                    Text(
                        text = timeBasedAlert,
                        style = MaterialTheme.typography.body2,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // Gestor de tareas
            item {
                Button(
                    onClick = { navController.navigate("task_manager") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF9C27B0))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("📋", style = MaterialTheme.typography.title2)
                        Text("Gestor de Tareas", style = MaterialTheme.typography.body2)
                    }
                }
            }

            // Timer Pomodoro
            item {
                Button(
                    onClick = { navController.navigate("pomodoro_timer") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE91E63))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("⏱️", style = MaterialTheme.typography.title2)
                        Text("Timer Pomodoro", style = MaterialTheme.typography.body2)
                    }
                }
            }

            // Recordatorios de ubicación
            item {
                Button(
                    onClick = { /* Location reminders */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00BCD4))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("📍", style = MaterialTheme.typography.title2)
                        Text("Alertas de Ubicación", style = MaterialTheme.typography.body2)
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