package com.example.diabetix.component

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.example.diabetix.data.Tracker
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun SugarChart(
    listTracker: List<Tracker>
) {
    val data = formatData(listTracker)
    // Check if data is valid and has at least two points for drawing a line
    if (data.size < 2) return


    Canvas(modifier = Modifier
        .fillMaxSize()
        .padding(48.dp)) {
        // Extract y values from data
        val yValues = data.map { it.second }
        val xValues = data.map { it.first }
        val minY = 0f
        val maxY = 100f

        // Normalize y-values to fit within the canvas
        val normalizedYValues = yValues.map { (it - minY) / (maxY - minY) }

        // Calculate spacing between points along the x-axis
        val pointSpacing = size.width / (data.size - 1)

        // Draw the X and Y axes
        drawLine(
            color = Color.Gray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height)
        )
        drawLine(
            color = Color.Gray,
            start = Offset(0f, 0f),
            end = Offset(0f, size.height)
        )

        // Create a path to draw the line connecting points
        val path = Path().apply {
            moveTo(0f, size.height * (1 - normalizedYValues[0])) // Start at the first point
            normalizedYValues.forEachIndexed { index, normalizedY ->
                val x = index * pointSpacing
                val y = size.height * (1 - normalizedY)
                lineTo(x, y)
            }
        }

        // Draw the line chart path with thicker line
        drawPath(
            path = path,
            color = GreenNormal,
            style = Stroke(width = 8f) // Set width to 8f for a thicker line
        )

        // Draw the X-axis labels (dates) below the X-axis
        xValues.forEachIndexed { index, label ->
            val x = index * pointSpacing
            drawContext.canvas.nativeCanvas.drawText(
                label,
                x,
                size.height + 30f, // Position below the x-axis, 30f pixels below the line
                Paint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 25f
                    textAlign = android.graphics.Paint.Align.CENTER
                }
            )
        }

        // Draw the Y-axis labels (values) to the left of the Y-axis
        val step = (maxY - minY) / 5 // Display 5 y-axis labels
        for (i in 0..5) {
            val value = minY + step * i
            val yPosition = size.height * (1 - (value - minY) / (maxY - minY)) // Normalized Y position
            drawContext.canvas.nativeCanvas.drawText(
                value.toInt().toString(),
                -20f, // Position to the left of the Y axis, 30f pixels from the axis line
                yPosition,
                Paint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 30f
                    textAlign = android.graphics.Paint.Align.RIGHT
                }
            )
        }
    }
}


fun formatData(trackers: List<Tracker>): List<Pair<String, Int>> {
    val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputDateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())

    return trackers.mapNotNull { tracker ->
        try {
            val date = inputDateFormat.parse(tracker.createdAt)
            val formattedDate = outputDateFormat.format(date)
            Pair(formattedDate, tracker.totalGlucose)
        } catch (e: Exception) {
            // Jika ada error saat parsing tanggal, data tersebut diabaikan
            null
        }
    }
}