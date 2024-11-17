package com.example.diabetix.presentation.analyze_result

import SemiCircularChart
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MyButton
import com.example.diabetix.data.FoodNutrition
import com.example.diabetix.presentation.login.LoginViewModel
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.RedNormal
import com.example.diabetix.ui.theme.YellowNormal

@Composable
fun AnalyzeResultScreen(
    navController: NavController,
    imagePath: String,
    nutrition: FoodNutrition
) {
    val image = remember {
        BitmapFactory.decodeFile(imagePath)
    }

    var levelColor by remember {
        mutableStateOf(GreenNormal)
    }

    levelColor = when (nutrition.levelGlucose) {
        "Gula Tinggi" -> RedNormal
        "Normal" -> YellowNormal
        "Gula Rendah" -> GreenNormal
        else -> GreenNormal
    }

    val viewModel = hiltViewModel<AnalyzeResultViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(GreenNormal),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                modifier = Modifier.padding(24.dp),
                text = "Detail Makanan",
                style = CustomTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // BOX image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(24.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {
            image?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Analyzed Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    contentScale = ContentScale.Crop
                )
            } ?: Text("Image not available")
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            //BOX NAMA MAKANAN
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(GreenLightHover)
            ) {
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFB0E4D3))
                        .align(Alignment.CenterEnd),
                    contentAlignment = Alignment.Center
                ) {
                    //NAMA MAKANAN
                    Text(
                        text = nutrition.foodName,
                        style = CustomTheme.typography.p3,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = "Nama makanan",
                        style = CustomTheme.typography.p4
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            //BOX JUMLAH GULA
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(GreenLightHover)
            ) {
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFB0E4D3))
                        .align(Alignment.CenterEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.matchParentSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {

                        //JUMLAH GULA
                        Text(
                            text = "${nutrition.glucose.toInt()} gr",
                            style = CustomTheme.typography.p3,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .width(140.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(levelColor),
                            contentAlignment = Alignment.Center
                        ) {
                            //JUMLAH DALAM GR
                            Text(
                                text = nutrition.levelGlucose,
                                style = CustomTheme.typography.p3,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                    }

                }
                Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = "Jumlah Gula",
                        style = CustomTheme.typography.p4
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //BOX NUTRISI LAINNYA
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .background(GreenLightHover),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFB0E4D3)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Nutrisi Lainnya",
                            style = CustomTheme.typography.p3,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Column(modifier = Modifier.padding(16.dp)) {

                        //KALORIIII
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFB0E4D3)),
                        ) {
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = "Kalori : ${nutrition.calories} kkal",
                                style = CustomTheme.typography.p4
                            )
                        }


                        //Lemak Total
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFB0E4D3)),
                        ) {
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = "Lemak total : ${nutrition.fat} kkal",
                                style = CustomTheme.typography.p4
                            )
                        }

                        //Karbohidrat Total
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFB0E4D3)),
                        ) {
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = "Karbohidrat total : ${nutrition.carbohydrate} kkal",
                                style = CustomTheme.typography.p4
                            )
                        }

                        //Protein Total
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFB0E4D3)),
                        ) {
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = "Protein : ${nutrition.protein} kkal",
                                style = CustomTheme.typography.p4
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(12.dp))

                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            //GRAFIK CIRCULAR
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SemiCircularChart(
                    currentValue = nutrition.currentGlucose.toInt(),
                    maxValue = nutrition.maxGlucose.toInt()
                )
            }

            //TEXT DIBAWAH GRAFIK
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                text = "Grafik di atas adalah jumlah gula yang Anda konsumsi apabila Anda mengonsumsi makanan ini",
                style = CustomTheme.typography.p4,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))


            //BOX SARAN
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .background(GreenLightHover),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFB0E4D3)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Saran",
                            style = CustomTheme.typography.p3,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = nutrition.advice,
                            style = CustomTheme.typography.p4,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            //TEXT DIBAWAH GRAFIK
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                text = "Apakah kamu ingin menambahkan Donat Cokelat ke dalam riwayat gula harianmu?",
                style = CustomTheme.typography.p4,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyButton(modifier = Modifier.weight(1f), onClick = {
                    navController.navigate("homepage") {
                        popUpTo(
                            navController.currentBackStackEntry?.destination?.route ?: "homepage"
                        ) {
                            inclusive = true
                        }
                    }
                }, text = "YA")
                Spacer(modifier = Modifier.width(8.dp))


                MyButton(modifier = Modifier.weight(1f), onClick = {
                    navController.navigate("homepage") {
                        popUpTo(
                            navController.currentBackStackEntry?.destination?.route ?: "homepage"
                        ) {
                            inclusive = true
                        }
                    }
                }, text = "TIDAK")
            }
        }
        Spacer(modifier = Modifier.height(64.dp))

    }
}