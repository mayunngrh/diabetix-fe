package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.diabetix.data.GlucoseTrackerDetail
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.RedNormal
import com.example.diabetix.ui.theme.YellowNormal
import java.text.Normalizer


@Composable
fun MakananItem(
    glucoseTrackerDetail: GlucoseTrackerDetail
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(GreenLightActive)
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(GreenLightActive)
                .align(Alignment.CenterEnd),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.matchParentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            GreenLightHover
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Spacer(modifier = Modifier.width(12.dp))
                    //JUMLAH GULA
                    Text(
                        text = glucoseTrackerDetail.glucose.toString(),
                        style = CustomTheme.typography.p4,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(80.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                when {
                                    glucoseTrackerDetail.glucose > 10 -> RedNormal
                                    glucoseTrackerDetail.glucose > 5 -> GreenNormal
                                    else -> YellowNormal
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        //KATEGORI KANDUNGAN GULA
                        Text(
                            text = when {
                                glucoseTrackerDetail.glucose > 10 -> "Gula Tinggi"
                                glucoseTrackerDetail.glucose > 5 -> "Normal"
                                else -> "Rendah"
                            },
                            style = CustomTheme.typography.p4,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
        Row(
            Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // NAMA MAKANAN
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = glucoseTrackerDetail.foodName,
                style = CustomTheme.typography.p4
            )
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

}