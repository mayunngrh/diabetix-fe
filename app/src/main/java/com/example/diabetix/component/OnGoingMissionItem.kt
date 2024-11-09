package com.example.diabetix.component

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun OnGoingMissionItem() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable {
                //DO SOMETHING WHEN CLICK
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.elevatedCardColors(GreenLightHover)
    ) {

        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            //FOTO AKTIVITAS
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(130.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        GreenLightActive
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = R.drawable.dummy_photo_mission,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Spacer(modifier = Modifier.height(8.dp))

                //NAMA AKTIVITAS
                Text(
                    text = "Jogging Pagi",
                    style = CustomTheme.typography.p3,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(modifier = Modifier.size(16.dp),painter = painterResource(id = R.drawable.ic_time), contentDescription = "")

                    //WAKTU AKTIVITAS
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = "15 Menit",
                        style = CustomTheme.typography.p4,
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))


                Row(verticalAlignment = Alignment.CenterVertically) {
                    //Level Aktivitas
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(75.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                GreenNormal
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Mudah",
                            style = CustomTheme.typography.p4,
                            fontSize = 11.sp,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    //ICON EXP
                    AsyncImage(modifier = Modifier.size(16.dp),model = R.drawable.ic_exp, contentDescription = "ICON EXP")

                    //JUMLAH EXP
                    Text(
                        modifier = Modifier.padding(start = 4.dp, end = 16.dp),
                        text = "150 xp",
                        style = CustomTheme.typography.p4,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        }
    }
}