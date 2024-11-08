package com.example.diabetix.presentation.daily_sugar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diabetix.component.GulaHarianItem
import com.example.diabetix.component.SugarChart
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLight
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun RiwayatSugarScreen(data: List<Pair<String, Float>>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        //TEXT PEMBUKA
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Grafik Konsumsi Gula 7 Hari Terakhir",
            style = CustomTheme.typography.p2,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        //Chart 7 Hari terakhir
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(GreenLight)
        ) {
            Column {

                Spacer(modifier = Modifier.height(12.dp))

                //NAMA BULAN
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "September",
                    style = CustomTheme.typography.p2,
                    fontWeight = FontWeight.Bold,
                    color = GreenNormal
                )

                //CHART
                SugarChart(data = data)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        //GULA HARIAN ITEM
        GulaHarianItem()

        GulaHarianItem()

        GulaHarianItem()

        Spacer(modifier = Modifier.height(50.dp))

    }
}