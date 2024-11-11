package com.example.diabetix.data.response

import com.example.diabetix.data.Article

data class ArticlesResponse (
    val articles:List<Article>,
    val message: String
)
