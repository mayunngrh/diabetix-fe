package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.diabetix.model.FrequencyOption
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralLightActive
import com.example.diabetix.ui.theme.NetralNormalActive

@Composable
fun FrequencySelection(
    selectedFrequency: Int = 0,
    onFrequencySelected: (Int) -> Unit
) {
    val frequencyOptions = listOf(
        FrequencyOption("Sangat Jarang Olahraga","1 kali/minggu", 1),
        FrequencyOption("Jarang Olahraga", "1-3 kali/minggu", 2),
        FrequencyOption("Sering Olahraga", "4-5 kali/minggu", 3),
        FrequencyOption("Sangat Sering Olahraga","5-7 kali/minggu", 4)
    )

    Column {
        frequencyOptions.forEach { frequency ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (frequency.value == selectedFrequency) GreenNormal else Color.Transparent
                    )
                    .border(
                        width = 2.dp,
                        if (frequency.value == selectedFrequency) GreenNormal else NetralLightActive,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        onFrequencySelected(frequency.value)
                    }
            ) {
                Column(modifier = Modifier.matchParentSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = frequency.category,
                        style = CustomTheme.typography.p2,
                        color = if (frequency.value == selectedFrequency) Color.White else Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = frequency.frequency,
                        style = CustomTheme.typography.p2,
                        color = if (frequency.value == selectedFrequency) Color.White else NetralLightActive,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}