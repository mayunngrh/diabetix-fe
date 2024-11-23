package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralLightActive

@Composable
fun StepIndicator(currentStep: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val stepColors = listOf(
            if (currentStep >= 1) GreenNormal else NetralLightActive,
            if (currentStep >= 2) GreenNormal else NetralLightActive,
            if (currentStep >= 3) GreenNormal else NetralLightActive,
            if (currentStep >= 4) GreenNormal else NetralLightActive,
            if (currentStep >= 5) GreenNormal else NetralLightActive,
        )

        stepColors.forEachIndexed { index, color ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(7.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color)
            )
            if (index < stepColors.size - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}