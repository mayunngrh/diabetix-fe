package com.example.diabetix.presentation.mission

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun MissionScreen(navController: NavController) {

    val viewModel = hiltViewModel<MissionViewModel>()
    var selectedTab by remember { mutableStateOf(0) }

    val mission by viewModel.missions.collectAsState()
    val onGoingMission by viewModel.onGoingMission.collectAsState()
    val finishedMission by viewModel.finishedMission.collectAsState()

    if (mission != null) {
        Column(
            modifier = Modifier.fillMaxSize() // This will ensure the column takes the entire available space
        ) {
            // HEADER BOX (page name & back button)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "Misi",
                    style = CustomTheme.typography.h2,
                    fontWeight = FontWeight.Bold,
                    color = GreenNormal
                )
                Row(
                    Modifier
                        .padding(horizontal = 24.dp)
                        .align(Alignment.BottomStart)) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp)

                            .clickable {
                                navController.navigate("homepage") {
                                    popUpTo(
                                        navController.currentBackStackEntry?.destination?.route
                                            ?: "homepage"
                                    ) {
                                        inclusive = true
                                    }
                                }
                            },
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = GreenNormal
                    )

                }

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

            // Mission content based on selected tab
            when (selectedTab) {
                0 -> {
                    OnGoingMissionScreen(onGoingMission, navController, mission.size)
                    println("ON GOING " + onGoingMission.size)
                    println("FINISH " + finishedMission.size)
                    println("ALL " + mission.size)
                }

                1 -> FinishedMissionScreen(finishedMission, navController)


            }
        }
    }
}

