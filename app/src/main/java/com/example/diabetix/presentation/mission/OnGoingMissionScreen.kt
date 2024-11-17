package com.example.diabetix.presentation.mission

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MissionSemiCircularChart
import com.example.diabetix.component.OnGoingMissionItem
import com.example.diabetix.data.Missions
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.google.gson.Gson

@Composable
fun OnGoingMissionScreen(
    missions: List<Missions>,
    navController: NavController,
    targetSize:Int
) {

    val currentMission = missions.size

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Spacer equivalent at the top
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // CHART TARGET HARIAN
        item {
            MissionSemiCircularChart(currentValue = currentMission, targetValue = targetSize)
        }

        // TEXT DIBAWAH CHART
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Ayo, selesaikan misi harianmu sekarang dan raih pencapaian hebat setiap hari!",
                style = CustomTheme.typography.p4,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )
        }

        // Spacer equivalent below the chart and text
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // ON GOING MISSION ITEM - Displaying list of missions
        items(missions) { mission ->
            OnGoingMissionItem(mission = mission) {
                val gson = Gson()
                val missionJson = gson.toJson(mission)
                val encodeMissionJson = Uri.encode(missionJson)
                navController.navigate("mission_detail/$encodeMissionJson")
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}
