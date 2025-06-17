// app/src/main/java/com/simu_wear/app/screens/SocialScreen.kt
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
fun SocialScreen(navController: NavController) {
    val notifications = listOf(
        "📱 Juan: ¿Vienes al gym?",
        "💬 Ana: Excelente entrenamiento!",
        "📍 María compartió su ubicación",
        "🎉 Pedro completó 10k pasos"
    )

    var selectedNotification by remember { mutableStateOf<String?>(null) }

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
                    text = "👥 Social",
                    style = MaterialTheme.typography.title2,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Text(
                    text = "Notificaciones recientes:",
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

            items(notifications.size) { index ->
                Button(
                    onClick = { selectedNotification = notifications[index] },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selectedNotification == notifications[index])
                            Color(0xFF4CAF50) else Color(0xFF424242)
                    )
                ) {
                    Text(
                        text = notifications[index],
                        style = MaterialTheme.typography.caption1,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                }
            }

            if (selectedNotification != null) {
                item {
                    Card(
                        onClick = { /* Click en respuestas rápidas */ },
                        modifier = Modifier.fillMaxWidth(),
                        backgroundPainter = CardDefaults.cardBackgroundPainter(
                            startBackgroundColor = Color(0xFF1E1E2E),
                            endBackgroundColor = Color(0xFF1E1E2E).copy(alpha = 0.8f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Respuestas rápidas:", style = MaterialTheme.typography.caption1)

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Button(
                                    onClick = { /* Enviar OK */ },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                                ) {
                                    Text("👍 OK", style = MaterialTheme.typography.caption2)
                                }

                                Button(
                                    onClick = { /* Enviar NO */ },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                                ) {
                                    Text("❌ NO", style = MaterialTheme.typography.caption2)
                                }
                            }
                        }
                    }
                }
            }

            item {
                Button(
                    onClick = { /* Compartir ubicación */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3))
                ) {
                    Text("📍 Compartir Ubicación", color = Color.White)
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