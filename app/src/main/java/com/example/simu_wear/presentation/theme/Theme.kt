// app/src/main/java/com/simu_wear/app/ui/theme/Theme.kt
package com.simu_wear.app.ui.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme

@Composable
fun SimuWearTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}