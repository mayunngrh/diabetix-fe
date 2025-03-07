package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.data.Bmi
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLight
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal
import com.example.diabetix.ui.theme.RedDarkActive
import com.example.diabetix.ui.theme.RedNormal
import com.example.diabetix.ui.theme.YellowNormal
import com.example.diabetix.ui.theme.YellowNormalActive
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun BmiCard(
    bmi: Bmi,
    onClickBMI:()->Unit
){
    val date = formatDate(bmi.createdAt)
    ElevatedCard(
        modifier = Modifier
            .height(165.dp)
            .width(160.dp)
            .clickable { onClickBMI() },
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
                        model = R.drawable.ic_bmi,
                        contentDescription = "Icon BMI"
                    )
                }
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "BMI",
                    style = CustomTheme.typography.p2,
                    color = GreenNormal
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = bmi.bmi.toString(),
                style = CustomTheme.typography.h1,
                fontWeight = FontWeight.Bold, color = GreenNormal,
                textAlign = TextAlign.Center
            )
            Row(modifier= Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            when(bmi.status){
                                "Underweight" -> GreenLightActive
                                "Overweight" -> YellowNormal
                                "Normal" -> GreenNormal
                                "Obesity I" -> YellowNormalActive
                                "Obesity I" -> RedNormal
                                "Obesity III" -> RedDarkActive
                                else -> GreenNormal
                            }

                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = bmi.status,
                        style = CustomTheme.typography.p4,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = when(bmi.status){
                            "Underweight" -> GreenNormal
                            else -> Color.White
                        }
                    )
                }
                Text(text = date, style = CustomTheme.typography.p4, fontSize = 9.sp, color = NetralNormal)
            }
        }
    }
}

fun formatDate(input: String): String {
    val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
    val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val date = ZonedDateTime.parse(input, inputFormatter)
    return date.format(outputFormatter)
}