// app/src/main/java/com/simu_wear/app/screens/TaskManagerScreen.kt
package com.simu_wear.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.*

@Composable
fun TaskManagerScreen(navController: NavController) {
    var tasks by remember {
        mutableStateOf(listOf(
            Task("Beber agua", false, "🥤"),
            Task("Ejercicio 30min", false, "🏃"),
            Task("Revisar emails", false, "📧"),
            Task("Llamar a mamá", false, "📞"),
            Task("Comprar comida", false, "🛒")
        ))
    }

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "📋 Mis Tareas",
                    style = MaterialTheme.typography.title2,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            item {
                val completedTasks = tasks.count { it.completed }
                Text(
                    text = "Completadas: $completedTasks/${tasks.size}",
                    style = MaterialTheme.typography.caption1,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

            items(tasks.size) { index ->
                val task = tasks[index]
                Button(
                    onClick = {
                        tasks = tasks.toMutableList().apply {
                            this[index] = task.copy(completed = !task.completed)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (task.completed) Color(0xFF4CAF50) else Color(0xFF424242)
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = task.icon,
                            style = MaterialTheme.typography.title3,
                            modifier = Modifier.padding(end = 8.dp)
                        )

                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.body2,
                            color = Color.White,
                            textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = if (task.completed) "✓" else "○",
                            style = MaterialTheme.typography.body1,
                            color = if (task.completed) Color.Green else Color.Gray
                        )
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

data class Task(
    val title: String,
    val completed: Boolean,
    val icon: String
)