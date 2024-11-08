package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun MakanHariIniItem(){
    ElevatedCard(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(GreenLightHover),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {

        Row {
            //Foto Makanan
            AsyncImage(
                modifier = Modifier.width(120.dp),
                model = R.drawable.dummy_photo_analyze,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            //Nama, level, dan jumlah gula
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(165.dp)
                    .padding(12.dp), verticalArrangement = Arrangement.Center
            ) {
                //NAMA MAKANAN
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    textAlign = TextAlign.Left,
                    text = "Donat Coklat",
                    style = CustomTheme.typography.p2,
                    fontWeight = FontWeight.Bold
                )

                //Jumlah dan Level Gula

                Row(verticalAlignment = Alignment.CenterVertically) {
                    //Level Gula
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .width(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                GreenNormal
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Normal",
                            style = CustomTheme.typography.p4,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // jumlah gula
                    Text(
                        text = "36gr",
                        style = CustomTheme.typography.p3,
                    )
                }
            }

            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center){
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "",
                    tint = GreenNormal
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}