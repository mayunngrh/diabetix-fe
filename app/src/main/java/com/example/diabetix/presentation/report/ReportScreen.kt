package com.example.diabetix.presentation.report

import CalendarView
import android.net.Uri
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.DaftarMakananItem
import com.example.diabetix.component.MakananItem
import com.example.diabetix.component.MissionItem
import com.example.diabetix.data.Report
import com.example.diabetix.presentation.analyze_result.MyState
import com.example.diabetix.presentation.profile.ProfileViewModel
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal
import com.example.diabetix.ui.theme.RedNormal
import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.time.format.TextStyle
import java.util.Locale
@Composable
fun ReportScreen(
    navController: NavController,

) {

    val viewModel = hiltViewModel<ReportViewModel>()
    val state by viewModel.state.collectAsState()
    val report by viewModel.report.collectAsState()

    LaunchedEffect(Unit) {
        delay(1500)
        viewModel.fetchReportData()
    }
    if (state is MyState.Success && report != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // HEADING SECTION
            item {
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
            }

            item { Spacer(modifier = Modifier.height(36.dp)) }

            // CALENDAR SECTION
            item {
                CalendarView(startDate = report?.startDate!!, endDate = report?.endDate!!)
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            // DAFTAR MAKANAN YANG DIKONSUMSI
            item {
                Text(
                    modifier = Modifier.padding(24.dp),
                    text = "Daftar Makanan yang Dikonsumsi",
                    style = CustomTheme.typography.p3,
                    fontWeight = FontWeight.Bold
                )
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            // DAFTAR MAKANAN ITEM
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item { Spacer(modifier = Modifier.width(12.dp)) }
                    items(report?.trackers!!) { tracker ->
                        DaftarMakananItem(tracker = tracker)
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Daftar Misi yang Diselesaikan
            item {
                Text(
                    text = "Daftar Misi yang Diselesaikan",
                    style = CustomTheme.typography.p3,
                    fontWeight = FontWeight.Bold
                )
            }


            item { Spacer(modifier = Modifier.height(12.dp)) }

            //MISI ITEM
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(report?.missions!!) { missions ->
                        MissionItem(missions = missions){
                            val gson = Gson()

                            val missionJson = gson.toJson(missions)
                            val encodeMissionJson = Uri.encode(missionJson)

                            navController.navigate("mission_detail/$encodeMissionJson")
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(36.dp)) }

            // BOX SARAN
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 24.dp)
                        .clip(RoundedCornerShape(16.dp))
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
                                text = report?.advice!!,
                                style = CustomTheme.typography.p4,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }

    }


    // LOADING AND PROSES
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is MyState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(NetralNormal.copy(0.4f)), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = GreenNormal, modifier = Modifier.size(64.dp))
                }
            }

            is MyState.Success -> {
                LaunchedEffect(Unit) {
                    delay(200)
                }
            }

            is MyState.Error -> {
                val errorMessage = (state as MyState.Error).message
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        Button(
                            onClick = { viewModel.resetState() },
                            colors = ButtonDefaults.buttonColors(GreenNormal)
                        ) {
                            Text("OK")
                        }
                    },
                    title = { Text(text = "Error") },
                    text = { Text(text = errorMessage ?: "Unknown error occurred.") }
                )
            }

            else -> { }
        }
    }
}
