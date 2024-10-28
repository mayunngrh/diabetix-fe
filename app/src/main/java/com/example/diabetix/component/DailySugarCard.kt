package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLight
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal

@Composable
fun DailySugarCard(){
    ElevatedCard(
        modifier = Modifier
            .height(165.dp)
            .width(160.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(GreenLight),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .height(180.dp)
                .width(160.dp)
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(
                            GreenNormal
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = R.drawable.ic_daily_sugar,
                        contentDescription = "Icon Sugar"
                    )
                }
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Gula Harian",
                    style = CustomTheme.typography.p3,
                    color = GreenNormal
                )
            }

            Row {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "26.7",
                    style = CustomTheme.typography.h1,
                    fontWeight = FontWeight.Bold, color = GreenNormal,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "MG",
                    style = CustomTheme.typography.p3,
                    color = GreenNormal,
                    textAlign = TextAlign.Center
                )
            }

            Row(modifier= Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            GreenNormal
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = "Normal",
                        style = CustomTheme.typography.p4,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                Text(text = "17 Mei 2024", style = CustomTheme.typography.p4, fontSize = 10.sp, color = NetralNormal)
            }
        }
    }
}