package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.YellowNormal

@Composable
fun MissionItem(){
    ElevatedCard(
        modifier = Modifier
            .width(230.dp)
            .height(280.dp)
            .padding(horizontal = 12.dp)
            .clickable {
                //DO SOMETHING WHEN CLICK
            },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Column {
                //FOTO
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                        .clip(
                            RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        ),
                    model = R.drawable.dummy_mission_photo,
                    contentDescription = "Foto misi",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                //JUDUL
                Text(
                    text = "Berenang selama 45 menit",
                    style = CustomTheme.typography.p4,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                //DESKRIPSI
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Berenang melatih hampir semua otot tubuh, meningkatkan kekuatan otot, fleksibilitas, serta kebugaran kardiovaskular.",
                    style = CustomTheme.typography.p4,
                    fontSize = 9.sp,
                    textAlign = TextAlign.Justify

                )

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = R.drawable.ic_exp,
                        contentDescription = "Icon EXP"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    //EXP AMOUNT
                    Text(
                        text = "300",
                        style = CustomTheme.typography.p3,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .clip(CircleShape)
                            .background(YellowNormal)
                        , contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = "Sedang",
                            style = CustomTheme.typography.p4,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }

                }
            }
        }
    }
}