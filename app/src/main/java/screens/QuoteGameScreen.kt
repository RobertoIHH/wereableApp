// app/src/main/java/com/simu_wear/app/screens/QuoteGameScreen.kt
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
fun QuoteGameScreen(navController: NavController) {
    val quotes = listOf(
        "💪 'El éxito es la suma de pequeños esfuerzos repetidos día tras día.' - Robert Collier",
        "🌟 'No se trata de ser perfecto, sino de ser mejor que ayer.' - Anónimo",
        "🚀 'El futuro pertenece a quienes creen en la belleza de sus sueños.' - Eleanor Roosevelt",
        "⚡ 'Tu única competencia eres tú mismo.' - Anónimo",
        "🎯 'Cada paso cuenta en el camino hacia tus metas.' - Anónimo",
        "🔥 'La constancia es la madre de todas las virtudes.' - Proverbio",
        "💎 'Las dificultades preparan a personas comunes para destinos extraordinarios.' - C.S. Lewis",
        "🌈 'La vida es 10% lo que te sucede y 90% cómo reaccionas a ello.' - Charles Swindoll"
    )

    var currentQuote by remember { mutableStateOf(quotes.random()) }

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
                    text = "💭 Inspiración Diaria",
                    style = MaterialTheme.typography.title2,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Card(
                    onClick = { /* Click en la tarjeta de cita */ },
                    modifier = Modifier.fillMaxWidth(),
                    backgroundPainter = CardDefaults.cardBackgroundPainter(
                        startBackgroundColor = Color(0xFF1E1E2E),
                        endBackgroundColor = Color(0xFF1E1E2E).copy(alpha = 0.8f)
                    )
                ) {
                    Text(
                        text = currentQuote,
                        style = MaterialTheme.typography.body1,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            item {
                Button(
                    onClick = { currentQuote = quotes.random() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
                ) {
                    Text("🔄 Nueva Cita", color = Color.White)
                }
            }

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