package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralLightActive

@Composable
fun InheritanceDiabet(
    selectedOption: String = "",
    onOptionSelected: (String) -> Unit
) {
    val option = listOf(
        "Ya",
        "Tidak"
    )

    Column {
        option.forEach { option ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (option == selectedOption) GreenNormal else Color.Transparent
                    )
                    .border(
                        width = 2.dp,
                        if (option == selectedOption) GreenNormal else NetralLightActive,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        onOptionSelected(option)
                    }
            ) {
                Column(modifier = Modifier.matchParentSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = option,
                        style = CustomTheme.typography.p2,
                        color = if (option == selectedOption) Color.White else Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}