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
fun GulaHarianItem() {
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
            //Jumlah Gula
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
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Gula Harian",
                        style = CustomTheme.typography.p3,
                        fontWeight = FontWeight.Bold,
                        color = GreenNormal
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    //JUMLAH GULA
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "43gr",
                        style = CustomTheme.typography.h1,
                        fontWeight = FontWeight.Bold,
                        color = GreenNormal
                    )

                    //BATAS GULA
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        textAlign = TextAlign.Right,
                        text = "/75gr",
                        style = CustomTheme.typography.p4,
                        fontSize = 10.sp,
                        color = GreenNormal
                    )
                }

            }
            Spacer(modifier = Modifier.width(12.dp))


            //Level Gula
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        GreenNormal
                    ), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Normal",
                    style = CustomTheme.typography.p4,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }


            //TULISAN LIHAT DETAIL
            Box(modifier = Modifier.fillMaxHeight().padding(bottom = 8.dp, end = 8.dp), contentAlignment = Alignment.BottomCenter){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Lihat Detail",
                        style = CustomTheme.typography.p4,
                        color = GreenNormal
                    )
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "", tint = GreenNormal)
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
}