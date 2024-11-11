package com.example.diabetix.component

import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.data.Article
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Composable
fun ArticleItem(
    article: Article,
    onArticleClick: () -> Unit
) {
    val convertDate = convertDate(article.date)
    val plainText = extractPlainText(article.body)
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(horizontal = 24.dp)
            .clickable {
                onArticleClick()
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
                    model = if (article.image != "") article.image else R.drawable.photo_no_article_picture,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Foto Artikel"
                )
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    //TANGGAL
                    Text(
                        text = convertDate,
                        style = CustomTheme.typography.p4,
                        fontSize = 9.sp,
                        color = NetralNormal
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    //JUDUL
                    Text(
                        text = article.title,
                        style = CustomTheme.typography.p4,
                        fontSize = 11.sp,
                        maxLines = 2,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Justify,
                    )



                    Spacer(modifier = Modifier.height(4.dp))

                    //BOX
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .border(1.dp, GreenNormal, RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            text = article.category,
                            style = CustomTheme.typography.p4,
                            fontSize = 9.sp,
                            color = GreenNormal
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    //DESKRIPSI

                    Text(
                        text = plainText,
                        style = CustomTheme.typography.p4,
                        fontSize = 8.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Justify,
                        color = NetralNormal
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.ic_like_outlined),
                                contentDescription = ""
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                text = "${article.likes}",
                                style = CustomTheme.typography.p4,
                                fontSize = 10.sp
                            )
                        }
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                text = "baca selengkapnya",
                                style = CustomTheme.typography.p4,
                                fontSize = 10.sp
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Right arrow"
                            )
                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

fun convertDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
    inputFormat.timeZone =
        TimeZone.getTimeZone("UTC") // Pastikan zona waktu sesuai dengan data Anda

    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val date = inputFormat.parse(dateString)

    return outputFormat.format(date)
}

fun extractPlainText(markdownText: String): String {
    val regex = Regex("[*#`>\\[\\]()\n\t]*")
    return markdownText
        .replace(regex, "")
        .replace("\\s+".toRegex(), " ")
        .trim()
}