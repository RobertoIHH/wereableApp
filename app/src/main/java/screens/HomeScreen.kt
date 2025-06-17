// app/src/main/java/com/simu_wear/app/screens/HomeScreen.kt
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
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(navController: NavController) {
    val currentTime = remember {
        mutableStateOf(SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()))
    }

    // Actualizar tiempo cada minuto
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(60000)
            currentTime.value = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        }
    }

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp), // MÁS PADDING para pantalla grande
            contentPadding = PaddingValues(vertical = 20.dp), // MÁS ESPACIO vertical
            verticalArrangement = Arrangement.spacedBy(12.dp), // MÁS ESPACIO entre elementos
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "🌟 SimuWear",
                    style = MaterialTheme.typography.title1,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Text(
                    text = "Tu compañero de bienestar",
                    style = MaterialTheme.typography.caption1,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

            item { Spacer(modifier = Modifier.height(12.dp)) } // MÁS ESPACIO

            // Botones principales - TAMAÑO AUMENTADO
            item {
                ModuleButton(
                    icon = "🎮",
                    title = "Entretenimiento",
                    subtitle = "Juegos y citas",
                    onClick = { navController.navigate("entertainment") }
                )
            }

            item {
                ModuleButton(
                    icon = "💚",
                    title = "Salud",
                    subtitle = "Bienestar y hábitos",
                    onClick = { navController.navigate("health") }
                )
            }

            item {
                ModuleButton(
                    icon = "👥",
                    title = "Social",
                    subtitle = "Conexiones rápidas",
                    onClick = { navController.navigate("social") }
                )
            }

            item {
                ModuleButton(
                    icon = "⚡",
                    title = "Utilidades",
                    subtitle = "Tareas y alertas",
                    onClick = { navController.navigate("utilities") }
                )
            }
        }
    }
}

@Composable
fun ModuleButton(
    icon: String,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.9f) // 90% del ancho para mejor proporción
            .height(70.dp), // ALTURA AUMENTADA para pantalla grande
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                style = MaterialTheme.typography.title1, // TAMAÑO AUMENTADO
                modifier = Modifier.padding(end = 12.dp) // MÁS ESPACIO
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.caption1,
                    color = Color.Gray
                )
            }
        }
    }
}