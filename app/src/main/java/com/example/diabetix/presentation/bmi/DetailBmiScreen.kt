package com.example.diabetix.presentation.bmi

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun DetailBmiScreen() {
    Spacer(modifier = Modifier.height(12.dp))
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)) {

        //BOX BMI
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
                        text = "BMI",
                        style = CustomTheme.typography.h2,
                        fontWeight = FontWeight.Bold,
                        color = GreenNormal
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //ANGKA BMI
                    Text(
                        text = "24.4",
                        style = CustomTheme.typography.h1,
                        fontWeight = FontWeight.Bold,
                        color = GreenNormal,
                        fontSize = 44.sp
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //BOX TINGGI dan MASSA
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            //TINGGi
            Box(
                modifier = Modifier
                    .weight(1f)
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
                            text = "Tinggi",
                            style = CustomTheme.typography.p2,
                            fontWeight = FontWeight.Bold,
                            color = GreenNormal
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //ANGKA TINGGi
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "181" ,
                                style = CustomTheme.typography.h2,
                                fontWeight = FontWeight.Bold,
                                color = GreenNormal,
                            )
                            Text(
                                text = "cm" ,
                                style = CustomTheme.typography.p4,
                                fontWeight = FontWeight.SemiBold,
                                color = GreenNormal,
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(12.dp))

                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            //MASSA
            Box(
                modifier = Modifier
                    .weight(1f)
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
                            text = "Massa",
                            style = CustomTheme.typography.p2,
                            fontWeight = FontWeight.Bold,
                            color = GreenNormal
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //ANGKA MASSA
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "80" ,
                                style = CustomTheme.typography.h2,
                                fontWeight = FontWeight.Bold,
                                color = GreenNormal,
                            )
                            Text(
                                text = "kg" ,
                                style = CustomTheme.typography.p4,
                                fontWeight = FontWeight.SemiBold,
                                color = GreenNormal,
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(12.dp))

                }
            }
        }

    }

}