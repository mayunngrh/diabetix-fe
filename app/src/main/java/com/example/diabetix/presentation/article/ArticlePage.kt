package com.example.diabetix.presentation.article

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.diabetix.component.ArticleItem
import com.example.diabetix.presentation.homepage.HomePageViewModel
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.google.gson.Gson

@Composable
fun ArticlePage(navController: NavController) {

    val viewModel = hiltViewModel<ArticleViewModel>()
    val articles by viewModel.articles.collectAsState()


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp)
            .background(GreenNormal),
        contentPadding = PaddingValues(0.dp)
    ) {

        item{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(GreenNormal)
                    .padding(24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    modifier = Modifier,
                    text = "Artikel",
                    style = CustomTheme.typography.h2,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }


        item{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color.White)
                    .padding(24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
            }
        }

        //ARTIKEL ITEMS
        items(articles) { article ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp)
            ) {
                ArticleItem(
                    article = article,
                    onArticleClick = {
                        val gson = Gson()
                        val articleJson = gson.toJson(article)
                        val encodedArticleJson = Uri.encode(articleJson)

                        navController.navigate("article_detail/$encodedArticleJson")
                    }
                )
            }
        }
        item{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(Color.White),
                contentAlignment = Alignment.BottomCenter
            ) {
            }
        }
    }
}