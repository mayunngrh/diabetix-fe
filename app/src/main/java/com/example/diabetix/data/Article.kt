package com.example.diabetix.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val image: String,
    val title: String,
    val category: String,
    val body: String,
    val likes: Int,
    val date: String,
    val createdAt: String,
    val updatedAt: String
) : Parcelable
