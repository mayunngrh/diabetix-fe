package com.example.diabetix.component

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun RiwayatBmiItem() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Left,
        text = "20 September",
        style = CustomTheme.typography.p4,
    )

    Spacer(modifier = Modifier.height(12.dp))

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
            //NILAI BMI
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
                Column(modifier = Modifier) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "BMI",
                        style = CustomTheme.typography.p3,
                        fontWeight = FontWeight.Bold,
                        color = GreenNormal
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    //NILAI BMI
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "24.4",
                        style = CustomTheme.typography.h1,
                        fontWeight = FontWeight.Bold,
                        color = GreenNormal
                    )
                }

            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {

                //1 LEVEL GULA DAN TANGGAL
                Row(verticalAlignment = Alignment.CenterVertically) {
                    //Level Gula
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .width(85.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                GreenNormal
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Normal",
                            style = CustomTheme.typography.p4,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    //TANGGAL
                    Text(
                        text = "14/09/2024",
                        style = CustomTheme.typography.p4,
                        fontSize = 12.sp,
                        color = GreenNormal
                    )

                }

                Spacer(modifier = Modifier.height(4.dp))

                //2 BOX Tinggi
                Row(verticalAlignment = Alignment.CenterVertically) {
                    //BOX
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .width(85.dp)
                            .border(1.dp, GreenNormal, RoundedCornerShape(16.dp))
                        , contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tinggi",
                            style = CustomTheme.typography.p4,
                            fontSize = 11.sp,
                            color = GreenNormal
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    //TINGGI
                    Text(
                        text = "181 cm",
                        style = CustomTheme.typography.p4,
                        fontSize = 12.sp,
                        color = GreenNormal
                    )

                }

                Spacer(modifier = Modifier.height(4.dp))

                //3 BOX Masa
                Row(verticalAlignment = Alignment.CenterVertically) {
                    //BOX
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .width(85.dp)
                            .border(1.dp, GreenNormal, RoundedCornerShape(16.dp))
                        , contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Massa",
                            style = CustomTheme.typography.p4,
                            fontSize = 11.sp,
                            color = GreenNormal
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    //TINGGI
                    Text(
                        text = "80 kg",
                        style = CustomTheme.typography.p4,
                        fontSize = 12.sp,
                        color = GreenNormal
                    )

                }
            }

            //TULISAN LIHAT DETAIL
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        tint = GreenNormal
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(24.dp))

}