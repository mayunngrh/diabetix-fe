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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.data.Missions
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal
import com.example.diabetix.ui.theme.RedNormal
import com.example.diabetix.ui.theme.YellowNormal

@Composable
fun MissionItem(
    missions: Missions,
    onMissionClick: () -> Unit
) {

    val plainText = extractPlainText(missions.mission.body)

    ElevatedCard(
        modifier = Modifier
            .width(230.dp)
            .height(280.dp)
            .padding(horizontal = 12.dp)
            .clickable {
                onMissionClick()
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
                    model = if (missions.mission.image != "") missions.mission.image else R.drawable.photo_no_article_picture,
                    contentDescription = "Foto misi",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                //JUDUL
                Text(
                    text = missions.mission.title,
                    style = CustomTheme.typography.p4,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                //DESKRIPSI
                Text(
                    text = plainText,
                    style = CustomTheme.typography.p4,
                    fontSize = 8.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                    color = NetralNormal
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
                        text = missions.mission.exp.toString(),
                        style = CustomTheme.typography.p3,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .clip(CircleShape)
                            .background(
                                when (missions.mission.category) {
                                    "Mudah" -> GreenNormal
                                    "Sedang" -> YellowNormal
                                    "Berat" -> RedNormal
                                    else -> GreenNormal
                                }
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = missions.mission.category,
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


