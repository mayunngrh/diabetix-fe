package com.example.diabetix.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun LinearExpChart(
    currentExp: Int,
    maxExp: Int
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp) // Adjust height as needed
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val barWidth = size.width
            val barHeight = size.height

            val progressWidth = (currentExp / maxExp.toFloat()) * barWidth

            // Draw the background bar
            drawRoundRect(
                color = Color(0xFFD9D9D9),
                size = size,
                cornerRadius = CornerRadius(36f, 36f)
            )

            // Draw the progress bar
            drawRoundRect(
                color = GreenNormal,
                size = Size(progressWidth, barHeight),
                cornerRadius = CornerRadius(36f, 36f)
            )
        }
    }
}