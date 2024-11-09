package com.example.diabetix.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun MissionSemiCircularChart(
    currentValue: Int,
    targetValue: Int,
    progressColor: Color = GreenNormal,
    backgroundColor: Color = GreenLightHover
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(175.dp)
    ) {
        // Canvas for drawing the semicircle
        Canvas(modifier = Modifier.size(175.dp)) {
            val startAngle = 155f
            val sweepAngle = 225f * (currentValue / targetValue.toFloat())

            // Background arc (full semicircle)
            drawArc(
                color = backgroundColor,
                startAngle = startAngle,
                sweepAngle = 225f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round)
            )

            // Progress arc
            drawArc(
                color = progressColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        // Centered text
        Text(
            modifier = Modifier,
            text = "$currentValue/$targetValue",
            style = CustomTheme.typography.h1,
            fontWeight = FontWeight.Bold,
            color = progressColor
        )
    }
}