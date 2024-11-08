package com.example.diabetix.presentation.daily_sugar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.diabetix.ui.theme.CustomTheme

@Composable
fun RiwayatSugarScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "RIWAYAT SCREEN",
            style = CustomTheme.typography.h1,
            fontWeight = FontWeight.Bold
        )
    }
}