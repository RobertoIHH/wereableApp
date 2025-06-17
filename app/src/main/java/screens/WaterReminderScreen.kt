// app/src/main/java/com/simu_wear/app/screens/WaterReminderScreen.kt
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
fun WaterReminderScreen(navController: NavController) {
    var reminderActive by remember { mutableStateOf(false) }
    var timeRemaining by remember { mutableStateOf(0) }

    LaunchedEffect(reminderActive) {
        if (reminderActive) {
            timeRemaining = 30 // 30 minutos
            while (timeRemaining > 0 && reminderActive) {
                delay(60000) // 1 minuto
                timeRemaining--
            }
            if (reminderActive) {
                // Aquí se activaría la notificación real
                reminderActive = false
            }
        }
    }

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
                    text = "💧 Recordatorios",
                    style = MaterialTheme.typography.title2,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color(0xFF2196F3).copy(alpha = 0.3f)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (reminderActive) "⏰ Recordatorio activo" else "💧 Hidratación",
                            style = MaterialTheme.typography.body1,
                            color = Color.White
                        )

                        if (reminderActive) {
                            Text(
                                text = "Próxima alerta en $timeRemaining min",
                                style = MaterialTheme.typography.caption1,
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        } else {
                            Text(
                                text = "Configura recordatorios cada 30 min",
                                style = MaterialTheme.typography.caption1,
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }

            item {
                Button(
                    onClick = { reminderActive = !reminderActive },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (reminderActive) Color.Red else Color(0xFF4CAF50)
                    )
                ) {
                    Text(
                        text = if (reminderActive) "🛑 Detener" else "▶️ Activar",
                        color = Color.White
                    )
                }
            }

            item {
                Text(
                    text = "Otros recordatorios:",
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
            }

            item {
                Button(
                    onClick = { /* Recordatorio de ejercicio */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF9800))
                ) {
                    Text("🏃 Recordatorio de Ejercicio", color = Color.White)
                }
            }

            item {
                Button(
                    onClick = { /* Recordatorio de descanso */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF9C27B0))
                ) {
                    Text("😴 Recordatorio de Descanso", color = Color.White)
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