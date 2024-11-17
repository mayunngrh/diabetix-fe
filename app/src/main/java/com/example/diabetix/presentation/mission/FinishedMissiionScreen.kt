package com.example.diabetix.presentation.mission

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diabetix.component.FinishedMissionItem
import com.example.diabetix.data.Missions
import com.google.gson.Gson


@Composable
fun FinishedMissionScreen(
    missions: List<Missions>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        items(missions) { mission ->
            FinishedMissionItem(mission) {
                val gson = Gson()

                val missionJson = gson.toJson(mission)
                val encodeMissionJson = Uri.encode(missionJson)

                navController.navigate("mission_detail/$encodeMissionJson")
            }

        }
    }
}