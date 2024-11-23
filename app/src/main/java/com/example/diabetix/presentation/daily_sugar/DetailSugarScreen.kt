package com.example.diabetix.presentation.daily_sugar

import SemiCircularChart
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MakanHariIniItem
import com.example.diabetix.component.RekomendasiAktivitasItem
import com.example.diabetix.data.Tracker
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLight
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal
import com.example.diabetix.ui.theme.RedNormal
import com.example.diabetix.ui.theme.YellowNormal

@Composable
fun DetailSugarScreen(
    currentTracker: Tracker
) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // CHART
            SemiCircularChart(
                currentValue = currentTracker.totalGlucose,
                maxValue = currentTracker.maxGlucose
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            // TEXT BELOW CHART
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                text = "Jumlah gula harian yang kamu konsumsi hari ini termasuk ke dalam kategori sebagai berikut:",
                style = CustomTheme.typography.p3,
                textAlign = TextAlign.Center
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            // LEVEL INDICATOR
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .width(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        when (currentTracker.status) {
                            "Tinggi" -> RedNormal
                            "Normal" -> YellowNormal
                            "Rendah" -> GreenNormal
                            else -> GreenNormal
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = currentTracker.status,
                    style = CustomTheme.typography.p3,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // MAKAN HARI INI SECTION
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left,
                text = "Makanan Hari ini",
                style = CustomTheme.typography.p2,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        // MAKAN HARI INI ITEMS
        items(currentTracker.glucoseTrackerDetails) {
            MakanHariIniItem(it)
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // REKOMENDASI AKTIVITAS SECTION
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left,
                text = "Rekomendasi Aktivitas",
                style = CustomTheme.typography.p2,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        // REKOMENDASI AKTIVITAS ITEMS
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                repeat(5) {
                    RekomendasiAktivitasItem()
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
