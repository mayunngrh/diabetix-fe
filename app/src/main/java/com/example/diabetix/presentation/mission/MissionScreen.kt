package com.example.diabetix.presentation.mission

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun MissionScreen(navController: NavController) {

    var selectedTab by remember { mutableStateOf(0) }


    Column (
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {

        // HEADER BOX (page name & back button)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.BottomStart),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
                tint = GreenNormal
            )
            Text(
                modifier = Modifier,
                text = "Misi",
                style = CustomTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                color = GreenNormal
            )
        }

        // TabRow
        TabRow(
            selectedTabIndex = selectedTab,
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab])
                        .height(4.dp),
                    color = GreenNormal
                )
            }
        ) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                Text(
                    "Berlangsung",
                    modifier = Modifier.padding(16.dp),
                    style = CustomTheme.typography.p3,
                    fontWeight = FontWeight.Medium,
                    color = if (selectedTab == 0) GreenNormal else Color.Black
                )
            }
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                Text(
                    "Selesai",
                    modifier = Modifier.padding(16.dp),
                    style = CustomTheme.typography.p3,
                    fontWeight = FontWeight.Medium,
                    color = if (selectedTab == 1) GreenNormal else Color.Black
                )
            }
        }


        when(selectedTab){
            0-> OnGoingMissionScreen()
            1-> FinishedMissionScreen()
        }
    }


}