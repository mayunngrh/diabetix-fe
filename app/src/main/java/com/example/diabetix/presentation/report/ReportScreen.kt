package com.example.diabetix.presentation.report

import CalendarView
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.DaftarMakananItem
import com.example.diabetix.component.MakananItem
import com.example.diabetix.component.MissionItem
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.RedNormal
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ReportScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        //HEADING SECTION
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
                text = "Laporan",
                style = CustomTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        //CALENDAR SECTION
        CalendarView()


        Spacer(modifier = Modifier.height(12.dp))


        //DAFTAR MAKANAN YANG DIKONSUMSI
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = "Daftar Makanan yang Dikonsumsi",
            style = CustomTheme.typography.p3,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(12.dp))

        //DAFTAR MAKANAN ITEM
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.width(24.dp))
            DaftarMakananItem()
            DaftarMakananItem()
            DaftarMakananItem()
            DaftarMakananItem()
            DaftarMakananItem()
            DaftarMakananItem()
            Spacer(modifier = Modifier.width(24.dp))


        }

        Spacer(modifier = Modifier.height(24.dp))


        //Daftar Misi yang Diselesaikan
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = "Daftar Misi yang Diselesaikan",
            style = CustomTheme.typography.p3,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.width(12.dp))
            MissionItem()
            MissionItem()
            MissionItem()
            MissionItem()
            MissionItem()
            MissionItem()
            MissionItem()
            Spacer(modifier = Modifier.width(12.dp))
        }


        Spacer(modifier = Modifier.height(36.dp))


        //BOX SARAN
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 24.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .background(GreenLightHover),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFB0E4D3)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Saran",
                        style = CustomTheme.typography.p3,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = "Kamu telah menjaga kebiasaan yang baik pada minggu ini. Berhasil menyelesaikan 4 misi dalam 1 minggu adalah sebuah pencapaian yang bagus. Namun, kamu harus tetap memperhatikan jumlah konsumsi gula harian mu. Stay Healthy \uD83D\uDE0A",
                        style = CustomTheme.typography.p4,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(80.dp))

    }

}
