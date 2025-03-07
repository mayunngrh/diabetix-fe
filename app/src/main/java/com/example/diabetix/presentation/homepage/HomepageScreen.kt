package com.example.diabetix.presentation.homepage

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.ArticleItem
import com.example.diabetix.component.BmiCard
import com.example.diabetix.component.DailySugarCard
import com.example.diabetix.component.MissionItem
import com.example.diabetix.presentation.login.LoginViewModel
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLight
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal
import com.example.diabetix.ui.theme.YellowNormal
import com.google.gson.Gson

@Composable
fun HomepageScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<HomePageViewModel>()
    val articles by viewModel.articles.collectAsState()
    val misions by viewModel.missions.collectAsState()
    val currentBmi by viewModel.currentBmi.collectAsState()
    val currentTracker by viewModel.currentTracker.collectAsState()
    val user by viewModel.user.collectAsState()

//    print("NILAI CurrentTRACKER = $currentTracker")
    print("NILAI USER SCREEN: $user")

//    print("NILAI CURRENT BMI SCREEN: $currentBmi")

    val firstName = user?.name?.split(" ")?.get(0)
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(0.dp)
    ) {
        //BOX PROFILE
        item {
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
                            model = R.drawable.vector_profile_dummy,
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
                                    text = "Halo ${firstName}",
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
                                    .background(GreenLightHover),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${user?.personalization?.age} Tahun",
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
                        currentBmi?.let {
                            BmiCard(bmi = it) {
                                navController.navigate("bmi")
                            }
                        }
                        currentTracker?.let{
                            DailySugarCard(it) {
                                navController.navigate("daily_sugar")
                            }
                        }
                    }
                }
            }
        }

        //FITUR ROW
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 36.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //CIRCLE FITUR 2 (Pindai Makanan)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(75.dp)
                            .clip(CircleShape)
                            .background(GreenNormal)
                            .clickable {
                                navController.navigate("analyze_page")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(56.dp),
                            model = R.drawable.ic_pindai,
                            contentDescription = "Kontrol"
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Pindai",
                        style = CustomTheme.typography.p1,
                        fontWeight = FontWeight.SemiBold,
                        color = GreenNormal
                    )
                }

                //CIRCLE FITUR 3 (laporan)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(75.dp)
                            .clip(CircleShape)
                            .background(GreenNormal)
                            .clickable {
                                navController.navigate("report")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(36.dp),
                            model = R.drawable.ic_laporan,
                            contentDescription = "Laporan"
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Laporan",
                        style = CustomTheme.typography.p1,
                        fontWeight = FontWeight.SemiBold,
                        color = GreenNormal
                    )
                }

                //CIRCLE FITUR 4 (Mission)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(75.dp)
                            .clip(CircleShape)
                            .background(GreenNormal)
                            .clickable {
                                navController.navigate("mission")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(64.dp),
                            model = R.drawable.ic_mission,
                            contentDescription = "Misi"
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Misi",
                        style = CustomTheme.typography.p1,
                        fontWeight = FontWeight.SemiBold,
                        color = GreenNormal
                    )
                }
            }
        }

        //CARD HOMEPAGE
        item {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 24.dp)
                    .clickable {
                        navController.navigate("article")
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
        }


        //ARTIKEL SECTION
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Artikel",
                    style = CustomTheme.typography.p2,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .clip(CircleShape)
                        .background(GreenLightActive)
                        .clickable {
                            navController.navigate("article")
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = "Lihat semua",
                        style = CustomTheme.typography.p3,
                        fontWeight = FontWeight.SemiBold,
                        color = GreenNormal
                    )
                }
            }
        }

        //ARTIKEL ITEMS
        items(articles) { article ->
            ArticleItem(article = article, onArticleClick = {
                val gson = Gson()

                val articleJson = gson.toJson(article)
                val encodedArticleJson = Uri.encode(articleJson)

                navController.navigate("article_detail/$encodedArticleJson")
            })
        }

        //MISSION SECTION
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Misi Harian",
                    style = CustomTheme.typography.p2,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .clip(CircleShape)
                        .background(GreenLightActive)
                        .clickable {
                            navController.navigate("mission")
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = "Lihat semua",
                        style = CustomTheme.typography.p3,
                        fontWeight = FontWeight.SemiBold,
                        color = GreenNormal
                    )
                }
            }
        }

        //MISION ITEMS
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 24.dp)
            ) {
                items(misions) { mission ->
                    MissionItem(missions = mission, onMissionClick = {
                        val gson = Gson()

                        val missionJson = gson.toJson(mission)
                        val encodeMissionJson = Uri.encode(missionJson)

                        navController.navigate("mission_detail/$encodeMissionJson")
                    })
                }
            }
        }

        //SPACER
        item {
            Spacer(modifier = Modifier.height(160.dp))
        }

    }
}
