// app/src/main/java/com/simu_wear/app/screens/MemoryGameScreen.kt
package com.simu_wear.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import kotlinx.coroutines.delay

@Composable
fun MemoryGameScreen(navController: NavController) {
    val emojis = listOf("🌟", "🎯", "🚀", "💫")
    val allCards = (emojis + emojis).shuffled()

    var cards by remember {
        mutableStateOf(allCards.mapIndexed { index, emoji ->
            MemoryCard(index, emoji, false, false)
        })
    }

    var flippedCards by remember { mutableStateOf(listOf<Int>()) }
    var matches by remember { mutableStateOf(0) }
    var gameWon by remember { mutableStateOf(false) }

    LaunchedEffect(flippedCards) {
        if (flippedCards.size == 2) {
            delay(1000)
            val firstCard = cards[flippedCards[0]]
            val secondCard = cards[flippedCards[1]]

            if (firstCard.emoji == secondCard.emoji) {
                cards = cards.map { card ->
                    if (card.id in flippedCards) card.copy(matched = true) else card
                }
                matches++
                if (matches == 4) gameWon = true
            }

            cards = cards.map { card ->
                if (card.id in flippedCards && !card.matched) card.copy(flipped = false) else card
            }
            flippedCards = emptyList()
        }
    }

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), // PADDING AJUSTADO
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp), // MÁS ESPACIO
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "🧠 Memoria",
                    style = MaterialTheme.typography.title2,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Text(
                    text = if (gameWon) "🎉 ¡Ganaste!" else "Pares: $matches/4",
                    style = MaterialTheme.typography.body2,
                    color = if (gameWon) Color.Green else Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

            item {
                // GRID AUMENTADO para pantalla grande
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier.size(160.dp), // TAMAÑO AUMENTADO
                    horizontalArrangement = Arrangement.spacedBy(6.dp), // MÁS ESPACIO
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(cards) { card ->
                        Box(
                            modifier = Modifier
                                .size(32.dp) // TAMAÑO AUMENTADO de tarjetas
                                .clip(RoundedCornerShape(6.dp))
                                .background(
                                    if (card.flipped || card.matched) Color(0xFF4CAF50) else Color(0xFF424242)
                                )
                                .clickable {
                                    if (!card.flipped && !card.matched && flippedCards.size < 2) {
                                        cards = cards.map {
                                            if (it.id == card.id) it.copy(flipped = true) else it
                                        }
                                        flippedCards = flippedCards + card.id
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (card.flipped || card.matched) card.emoji else "?",
                                style = MaterialTheme.typography.body1, // TAMAÑO AUMENTADO
                                color = Color.White
                            )
                        }
                    }
                }
            }

            if (gameWon) {
                item {
                    Button(
                        onClick = {
                            val newCards = allCards.shuffled()
                            cards = newCards.mapIndexed { index, emoji ->
                                MemoryCard(index, emoji, false, false)
                            }
                            flippedCards = emptyList()
                            matches = 0
                            gameWon = false
                        },
                        modifier = Modifier.fillMaxWidth(0.8f), // ANCHO CONTROLADO
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
                    ) {
                        Text("🔄 Jugar de Nuevo", color = Color.White)
                    }
                }
            }

            item {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth(0.8f), // ANCHO CONTROLADO
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                ) {
                    Text("← Volver", color = Color.White)
                }
            }
        }
    }
}

data class MemoryCard(
    val id: Int,
    val emoji: String,
    val flipped: Boolean,
    val matched: Boolean
)