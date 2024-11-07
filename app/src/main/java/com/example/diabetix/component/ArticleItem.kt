package com.example.diabetix.component

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.NetralNormal

@Composable
fun ArticleItem() {
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
}

@Preview(showBackground = true)
@Composable
fun test(){
    ArticleItem()
}