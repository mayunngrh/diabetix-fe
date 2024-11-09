package com.example.diabetix.presentation.mission

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.example.diabetix.component.FinishedMissionItem


@Composable
fun FinishedMissionScreen() {

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        //Finished Mission Item
        FinishedMissionItem()
        FinishedMissionItem()
        FinishedMissionItem()
        FinishedMissionItem()
        FinishedMissionItem()
        FinishedMissionItem()
        FinishedMissionItem()
    }
}