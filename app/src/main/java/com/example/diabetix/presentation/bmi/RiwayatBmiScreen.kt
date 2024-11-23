package com.example.diabetix.presentation.bmi


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diabetix.component.RiwayatBmiItem
import com.example.diabetix.data.Bmi
import androidx.compose.foundation.lazy.items


@Composable
fun RiwayatBmiScreen(
    history: List<Bmi?>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize() // Ensure the LazyColumn gets the remaining height
        ) {
            items(history) { bmi ->
                if (bmi != null) {
                    RiwayatBmiItem(bmi)
                }
            }
        }
    }

}