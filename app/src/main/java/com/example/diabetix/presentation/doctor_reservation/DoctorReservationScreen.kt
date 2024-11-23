package com.example.diabetix.presentation.doctor_reservation

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.HintDatePicker
import com.example.diabetix.component.MyButton
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import java.util.Date

@Composable
fun DoctorReservationScreen() {

    var tanggal by remember {
        mutableStateOf<Date?>(null)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        //HEADER SECTION
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
                text = "Reservasi",
                style = CustomTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                color = GreenNormal
            )
        }

        Column(modifier = Modifier.padding(24.dp)) {


            Spacer(modifier = Modifier.height(12.dp))

            //Dokter foto
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clickable {
                    },
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.elevatedCardElevation(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(120.dp)
                                .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)),
                            model = R.drawable.dummy_doctor_photo,
                            contentScale = ContentScale.Crop,
                            contentDescription = "Foto Dokter"
                        )
                        Column(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalArrangement = Arrangement.Center
                        ) {

                            Spacer(modifier = Modifier.height(12.dp))

                            //NAMA DOKTER
                            Text(
                                text = "dr. Riska ayu",
                                style = CustomTheme.typography.p3,
                                maxLines = 1,
                                fontWeight = FontWeight.SemiBold,
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row (verticalAlignment = Alignment.CenterVertically) {

                                //PENGALAMAN TAHUN
                                Text(
                                    text = "Penyakit Dalam",
                                    style = CustomTheme.typography.p4,
                                    maxLines = 1,
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Spacer(modifier = Modifier.width(4.dp))
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Pilih Jadwal",
                style = CustomTheme.typography.p2,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(24.dp))

            HintDatePicker(hint = "Pilih Jadwal", onValueChange = { tanggal = it }, value = tanggal)

            Spacer(modifier = Modifier.height(36.dp))


            Text(
                text = "Pilih Waktu",
                style = CustomTheme.typography.p2,
                maxLines = 1,
            )



            Spacer(modifier = Modifier.height(24.dp))
            MyButton(modifier = Modifier, onClick = {
            }, text = "Reservasi")
            Spacer(modifier = Modifier.height(48.dp))


        }
    }


}