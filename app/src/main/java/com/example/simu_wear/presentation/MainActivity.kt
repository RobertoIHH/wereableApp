// app/src/main/java/com/simu_wear/app/MainActivity.kt
package com.simu_wear.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.simu_wear.app.screens.*
import com.simu_wear.app.ui.theme.SimuWearTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimuWearTheme {
                SimuWearApp()
            }
        }
    }
}

@Composable
fun SimuWearApp() {
    val navController = rememberSwipeDismissableNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController) }
        composable("entertainment") { EntertainmentScreen(navController) }
        composable("health") { HealthScreen(navController) }
        composable("social") { SocialScreen(navController) }
        composable("utilities") { UtilitiesScreen(navController) }
        composable("quote_game") { QuoteGameScreen(navController) }
        composable("memory_game") { MemoryGameScreen(navController) }
        composable("water_reminder") { WaterReminderScreen(navController) }
        composable("task_manager") { TaskManagerScreen(navController) }
        composable("puzzle_game") { PuzzleGameScreen(navController) }
        composable("pomodoro_timer") { PomodoroTimerScreen(navController) }
    }
}