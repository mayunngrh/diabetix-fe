package com.example.diabetix.presentation.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        //BOX PROFILE
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

        //FITUR ROW
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 36.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //CIRCLE FITUR 1 (BMI)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(GreenNormal),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = R.drawable.ic_bmi_dumbell,
                        contentDescription = "BMI checker ic"
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "BMI", style = CustomTheme.typography.p3, color = GreenNormal)
            }

            //CIRCLE FITUR 2 (Pindai Makanan)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(GreenNormal)
                        .clickable {
                                   navController.navigate("analyze_page")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(model = R.drawable.ic_pindai, contentDescription = "Pindai Makanan")
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Pindai", style = CustomTheme.typography.p3, color = GreenNormal)
            }

            //CIRCLE FITUR 3 (laporan)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(GreenNormal),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(model = R.drawable.ic_laporan, contentDescription = "Laporan")
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Laporan", style = CustomTheme.typography.p3, color = GreenNormal)
            }

            //CIRCLE FITUR 4 (Mission)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(GreenNormal),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(model = R.drawable.ic_mission, contentDescription = "Laporan")
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Misi", style = CustomTheme.typography.p3, color = GreenNormal)
            }
        }


        //CARD HOMEPAGE
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 24.dp)
                .clickable {
                    //DO SOMETHING WHEN CLICK
                },
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = R.drawable.card_homepage,
                    contentDescription = ""
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        //ARTIKEL SECTION
        Row(
            Modifier
                .fillMaxWidth()
                .padding(24.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Artikel", style = CustomTheme.typography.p2, fontWeight = FontWeight.Bold)
            Text(modifier = Modifier.clickable {
                //DO SOMETHING WHEN CLICK MORE ARTIKEL
            }, text = "lihat lebih banyak", style = CustomTheme.typography.p4, color = GreenNormal)
        }

        //ARTIKEL ITEM 1
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(horizontal = 24.dp)
                .clickable {
                    //DO SOMETHING WHEN CLICK
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
                        model = R.drawable.dummy_article_photo,
                        contentScale = ContentScale.Crop,
                        contentDescription = "Foto Artikel"
                    )
                    Column(modifier = Modifier.padding(12.dp)) {
                        //TANGGAL
                        Text(
                            text = "01/09/2024",
                            style = CustomTheme.typography.p4,
                            fontSize = 9.sp,
                            color = NetralNormal
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        //JUDUL
                        Text(
                            text = "Olahraga yang Efektif Menurunkan Kadar Gula Darah",
                            style = CustomTheme.typography.p4,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Justify,
                        )

                        Spacer(modifier = Modifier.height(4.dp))


                        //DESKRIPSI
                        Text(
                            text = "Jenis-jenis olahraga yang paling efektif untuk membantu menurunkan gula darah, seperti latihan kardio, yoga, dan latihan beban. Disertai dengan ...",
                            style = CustomTheme.typography.p4,
                            fontSize = 8.sp,
                            textAlign = TextAlign.Justify,
                            color = NetralNormal
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(modifier = Modifier.size(16.dp), painter = painterResource(id = R.drawable.ic_like_outlined), contentDescription = "")
                                Text(modifier = Modifier.padding(horizontal = 4.dp), text = "293", style = CustomTheme.typography.p4, fontSize = 10.sp)
                            }
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                                Text(modifier = Modifier.padding(horizontal = 4.dp), text = "baca selengkapnya", style = CustomTheme.typography.p4, fontSize = 10.sp)
                                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Right arrow")
                            }
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        //ARTIKEL ITEM 2
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(horizontal = 24.dp)
                .clickable {
                    //DO SOMETHING WHEN CLICK
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
                        model = R.drawable.dummy_article_photo,
                        contentScale = ContentScale.Crop,
                        contentDescription = "Foto Artikel"
                    )
                    Column(modifier = Modifier.padding(12.dp)) {
                        //TANGGAL
                        Text(
                            text = "01/09/2024",
                            style = CustomTheme.typography.p4,
                            fontSize = 9.sp,
                            color = NetralNormal
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        //JUDUL
                        Text(
                            text = "Olahraga yang Efektif Menurunkan Kadar Gula Darah",
                            style = CustomTheme.typography.p4,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Justify,
                        )

                        Spacer(modifier = Modifier.height(4.dp))


                        //DESKRIPSI
                        Text(
                            text = "Jenis-jenis olahraga yang paling efektif untuk membantu menurunkan gula darah, seperti latihan kardio, yoga, dan latihan beban. Disertai dengan ...",
                            style = CustomTheme.typography.p4,
                            fontSize = 8.sp,
                            textAlign = TextAlign.Justify,
                            color = NetralNormal
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(modifier = Modifier.size(16.dp), painter = painterResource(id = R.drawable.ic_like_outlined), contentDescription = "")
                                Text(modifier = Modifier.padding(horizontal = 4.dp), text = "293", style = CustomTheme.typography.p4, fontSize = 10.sp)
                            }
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                                Text(modifier = Modifier.padding(horizontal = 4.dp), text = "baca selengkapnya", style = CustomTheme.typography.p4, fontSize = 10.sp)
                                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Right arrow")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //ARTIKEL ITEM 3
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(horizontal = 24.dp)
                .clickable {
                    //DO SOMETHING WHEN CLICK
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
                        model = R.drawable.dummy_article_photo,
                        contentScale = ContentScale.Crop,
                        contentDescription = "Foto Artikel"
                    )
                    Column(modifier = Modifier.padding(12.dp)) {
                        //TANGGAL
                        Text(
                            text = "01/09/2024",
                            style = CustomTheme.typography.p4,
                            fontSize = 9.sp,
                            color = NetralNormal
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        //JUDUL
                        Text(
                            text = "Olahraga yang Efektif Menurunkan Kadar Gula Darah",
                            style = CustomTheme.typography.p4,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Justify,
                        )

                        Spacer(modifier = Modifier.height(4.dp))


                        //DESKRIPSI
                        Text(
                            text = "Jenis-jenis olahraga yang paling efektif untuk membantu menurunkan gula darah, seperti latihan kardio, yoga, dan latihan beban. Disertai dengan ...",
                            style = CustomTheme.typography.p4,
                            fontSize = 8.sp,
                            textAlign = TextAlign.Justify,
                            color = NetralNormal
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(modifier = Modifier.size(16.dp), painter = painterResource(id = R.drawable.ic_like_outlined), contentDescription = "")
                                Text(modifier = Modifier.padding(horizontal = 4.dp), text = "293", style = CustomTheme.typography.p4, fontSize = 10.sp)
                            }
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                                Text(modifier = Modifier.padding(horizontal = 4.dp), text = "baca selengkapnya", style = CustomTheme.typography.p4, fontSize = 10.sp)
                                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Right arrow")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(150.dp))
    }
}