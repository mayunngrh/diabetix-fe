package com.example.diabetix.presentation.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.BmiCard
import com.example.diabetix.component.DailySugarCard
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLight
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal

@Composable
fun HomepageScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(345.dp)
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .background(GreenNormal)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp),
                model = R.drawable.bg_profile_card,
                contentDescription = "Blop Background Profile Card",
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        model = R.drawable.photo_profile_dummy,
                        contentDescription = "Foto profil pengguna"
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Column {
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                                text = "Halo Indra",
                                style = CustomTheme.typography.h2,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(4.dp),
                                model = R.drawable.ic_notifications,
                                contentDescription = "Notifications Button"
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(4.dp),
                                model = R.drawable.ic_gifts,
                                contentDescription = "Gift Button"
                            )
                        }
                        Box(
                            modifier = Modifier
                                .height(24.dp)
                                .width(100.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(
                                    GreenLightHover
                                ), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "27 Tahun",
                                style = CustomTheme.typography.p4,
                                fontWeight = FontWeight.SemiBold,
                                color = GreenNormal
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                //ROW BMI DAN DAILY SUGAR
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BmiCard()
                    DailySugarCard()
                }

            }
        }
    }
}