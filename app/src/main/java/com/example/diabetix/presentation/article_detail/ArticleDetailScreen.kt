package com.example.diabetix.presentation.mission_detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MyButton
import com.example.diabetix.data.Article
import com.example.diabetix.presentation.analyze_result.MyState
import com.example.diabetix.presentation.article.ArticleViewModel
import com.example.diabetix.presentation.article_detail.ArticleDetailViewModel
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal
import dev.jeziellago.compose.markdowntext.MarkdownText
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


@Composable
fun ArticleDetailScreen(
    navController: NavController,
    article: Article
) {
    var convertDate = convertDate(article.date)

    var isLiked by remember {
        mutableStateOf(article.isLikedByCurrentUser)
    }

    var likes by remember {
        mutableStateOf(article.likes)
    }

    val markDownText = article.body.trimIndent()
    val viewModel = hiltViewModel<ArticleDetailViewModel>()
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                model = R.drawable.bg_profile_card,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                //HEADER SECTION
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(vertical = 24.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomStart)
                            .clickable {
                                navController.navigate("article")
                            },
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = Color.White
                    )
                    Text(
                        modifier = Modifier,
                        text = "Detail Artikel",
                        style = CustomTheme.typography.h2,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                //FOTO MISI AKTIVITAS
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    model = if (article.image != "") article.image else R.drawable.photo_no_article_picture,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(24.dp))


                //ROW EXP and CALORIES
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    //BOX
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .border(1.dp, GreenNormal, RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            text = article.category,
                            style = CustomTheme.typography.p4,
                            color = GreenNormal
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    //JUMLAH EXP
                    Text(
                        text = convertDate,
                        style = CustomTheme.typography.p4,
                        fontSize = 12.sp,

                        )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = GreenNormal
                )

                Spacer(modifier = Modifier.height(24.dp))

                //JUDUL
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = article.title,
                    style = CustomTheme.typography.h2,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                MarkdownText(
                    modifier = Modifier.fillMaxSize(), markdown = markDownText,
                    style = CustomTheme.typography.p4
                )

                Spacer(modifier = Modifier.height(36.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(36.dp)
                            .clickable {
                                if (isLiked) {
                                    viewModel.deleteLikes(article.id)
                                    isLiked = !isLiked
                                    likes--
                                } else {
                                    viewModel.createLikes(article.id)
                                    isLiked = !isLiked
                                    likes++
                                }
                            },
                        model = when (isLiked) {
                            true -> R.drawable.ic_liked
                            else -> R.drawable.ic_unliked
                        },
                        contentDescription = ""
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = likes.toString(),
                        style = CustomTheme.typography.h2,
                        color = GreenNormal,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(64.dp))
            }
        }

    }

    //LOADING AND PROSES
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is MyState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(NetralNormal.copy(0.4f)), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = GreenNormal, modifier = Modifier.size(64.dp))
                }

            }

            is MyState.Success -> {
                LaunchedEffect(Unit) {
                    delay(200)
                }
            }

            is MyState.Error -> {
                val errorMessage = (state as MyState.Error).message
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        Button(
                            onClick = { viewModel.resetState() },
                            colors = ButtonDefaults.buttonColors(
                                GreenNormal
                            )
                        ) {
                            Text("OK")
                        }
                    },
                    title = {
                        Text(text = "Error")
                    },
                    text = {
                        Text(text = errorMessage ?: "Unknown error occurred.")
                    }
                )
            }

            else -> {
                //
            }
        }
    }
}

fun convertDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
    inputFormat.timeZone =
        TimeZone.getTimeZone("UTC") // Pastikan zona waktu sesuai dengan data Anda

    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val date = inputFormat.parse(dateString)

    return outputFormat.format(date)
}