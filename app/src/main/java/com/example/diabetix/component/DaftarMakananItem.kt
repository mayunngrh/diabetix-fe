package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.diabetix.data.Tracker
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.RedNormal

@Composable
fun DaftarMakananItem(
    tracker: Tracker
) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .heightIn(min = 100.dp, max = 500.dp) // Set a bounded height
            .clip(RoundedCornerShape(16.dp))
            .background(GreenLightHover)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header item (Date)
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFB0E4D3)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "5 September 2024",
                        style = CustomTheme.typography.p3,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // List of Makanan Items
            items(tracker.glucoseTrackerDetails) { glucoseTrackerDetail ->
                MakananItem(glucoseTrackerDetail = glucoseTrackerDetail)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Total Row
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Total",
                        style = CustomTheme.typography.p4,
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    // Box for total glucose
                    Box(
                        modifier = Modifier
                            .height(35.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (tracker.totalGlucose > 50) RedNormal else GreenNormal
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            // Display total glucose amount
                            Text(
                                text = "${tracker.totalGlucose}gr",
                                style = CustomTheme.typography.p3,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            // Max glucose
                            Text(
                                text = "/50gr",
                                style = CustomTheme.typography.p3,
                                fontSize = 8.sp,
                                color = Color.White
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

