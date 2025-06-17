// app/src/main/java/com/simu_wear/app/screens/PuzzleGameScreen.kt
package com.simu_wear.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.*
import kotlin.math.abs

@Composable
fun PuzzleGameScreen(navController: NavController) {
    var numbers by remember { mutableStateOf((1..8).toList() + 0) }
    var moves by remember { mutableStateOf(0) }
    val winCondition = (1..8).toList() + 0
    var gameWon by remember { mutableStateOf(false) }

    fun canMove(index: Int): Boolean {
        val emptyIndex = numbers.indexOf(0)
        val row = index / 3
        val col = index % 3
        val emptyRow = emptyIndex / 3
        val emptyCol = emptyIndex % 3

        return (row == emptyRow && abs(col - emptyCol) == 1) ||
                (col == emptyCol && abs(row - emptyRow) == 1)
    }

    fun moveNumber(index: Int) {
        if (canMove(index)) {
            val emptyIndex = numbers.indexOf(0)
            val newNumbers = numbers.toMutableList()
            newNumbers[emptyIndex] = numbers[index]
            newNumbers[index] = 0
            numbers = newNumbers
            moves++
            gameWon = numbers == winCondition
        }
    }

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "🧩 Puzzle Numérico",
                    style = MaterialTheme.typography.title2,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Text(
                    text = if (gameWon) "🎉 ¡Completado!" else "Movimientos: $moves",
                    style = MaterialTheme.typography.body2,
                    color = if (gameWon) Color.Green else Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

            item {
                // GRID 3x3 AUMENTADO para pantalla grande
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.size(180.dp), // TAMAÑO AUMENTADO
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(numbers.size) { index ->
                        val number = numbers[index]
                        Box(
                            modifier = Modifier
                                .size(54.dp) // TAMAÑO AUMENTADO de casillas
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (number == 0) Color.Transparent
                                    else if (canMove(index)) Color(0xFF2196F3)
                                    else Color(0xFF424242)
                                )
                                .clickable { moveNumber(index) },
                            contentAlignment = Alignment.Center
                        ) {
                            if (number != 0) {
                                Text(
                                    text = number.toString(),
                                    style = MaterialTheme.typography.title2, // TEXTO MÁS GRANDE
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }

            item {
                Button(
                    onClick = {
                        numbers = (1..8).toList().shuffled() + 0
                        moves = 0
                        gameWon = false
                    },
                    modifier = Modifier.fillMaxWidth(0.7f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
                ) {
                    Text("🔄 Mezclar", color = Color.White)
                }
            }

            item {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth(0.7f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                ) {
                    Text("← Volver", color = Color.White)
                }
            }
        }
    }
}