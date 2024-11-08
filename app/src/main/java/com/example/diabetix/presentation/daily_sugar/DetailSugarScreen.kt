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
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLight
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal

@Composable
fun DetailSugarScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //CHART
        SemiCircularChart(currentValue = 64, maxValue = 75)

        //TEXT DIBAWAH CHART
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            text = "Jumlah gula harian yang kamu konsumsi hari ini termasuk ke dalam kategori sebagai berikut :",
            style = CustomTheme.typography.p3,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        //LEVEL INDICATOR
        Box(
            modifier = Modifier
                .height(30.dp)
                .width(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    GreenNormal
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Normal",
                style = CustomTheme.typography.p3,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        //MAKAN HARI INI SECTION
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left,
            text = "Makan Hari ini",
            style = CustomTheme.typography.p2,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))


        //MAKAN HARI INI ITEM
        MakanHariIniItem()
        MakanHariIniItem()
        MakanHariIniItem()


        Spacer(modifier = Modifier.height(24.dp))

        //REKOMENDASI AKTIVITAS
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left,
            text = "Rekomendasi Aktivitas",
            style = CustomTheme.typography.p2,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        //REKOMENDASI AKTIVITAS ITEM
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            RekomendasiAktivitasItem()
            RekomendasiAktivitasItem()
            RekomendasiAktivitasItem()
            RekomendasiAktivitasItem()
            RekomendasiAktivitasItem()

        }

        Spacer(modifier = Modifier.height(50.dp))

    }
}