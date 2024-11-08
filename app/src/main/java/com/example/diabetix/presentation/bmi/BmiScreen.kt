package com.example.diabetix.presentation.bmi

import androidx.compose.foundation.background
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
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.presentation.daily_sugar.DetailSugarScreen
import com.example.diabetix.presentation.daily_sugar.RiwayatSugarScreen
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun BmiScreen(navController: NavController){
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(GreenNormal)
                .padding(24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.BottomStart),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
                tint = Color.White
            )
            Text(
                modifier = Modifier,
                text = "BMI",
                style = CustomTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            AsyncImage(
                modifier = Modifier
                    .size(42.dp)
                    .align(Alignment.BottomEnd),
                model = R.drawable.ic_add,
                contentDescription = "",
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
                    "Detail",
                    modifier = Modifier.padding(16.dp),
                    style = CustomTheme.typography.p3,
                    fontWeight = FontWeight.Medium,
                    color = if (selectedTab == 0) GreenNormal else Color.Black
                )
            }
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                Text(
                    "Riwayat",
                    modifier = Modifier.padding(16.dp),
                    style = CustomTheme.typography.p3,
                    fontWeight = FontWeight.Medium,
                    color = if (selectedTab == 0) GreenNormal else Color.Black
                )
            }
        }


        when (selectedTab) {
            0 -> DetailBmiScreen()
            1 -> RiwayatBmiScreen()
        }
    }
}