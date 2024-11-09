package com.example.diabetix.presentation.mission

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MissionSemiCircularChart
import com.example.diabetix.component.OnGoingMissionItem
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun OnGoingMissionScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        //CHART TARGET HARIAN
        MissionSemiCircularChart(currentValue = 2, targetValue = 3)

        //TEXT DIBAWAH CHART
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Ayo, selesaikan misi harianmu sekarang dan raih pencapaian hebat setiap hari!",
            style = CustomTheme.typography.p4,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))


        //ON GOING MISSION ITEM
        OnGoingMissionItem()
        Spacer(modifier = Modifier.height(12.dp))

        OnGoingMissionItem()
        Spacer(modifier = Modifier.height(12.dp))

        OnGoingMissionItem()
        Spacer(modifier = Modifier.height(12.dp))

        OnGoingMissionItem()
        Spacer(modifier = Modifier.height(12.dp))

        OnGoingMissionItem()
        Spacer(modifier = Modifier.height(12.dp))

        OnGoingMissionItem()
    }
}