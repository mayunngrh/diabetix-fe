package com.example.diabetix.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Missions(
    val mission: MissionDetail,
    val isDone:Boolean
) : Parcelable

@Parcelize
data class MissionDetail(
    val id: Int,
    val image: String,
    val exp: Int,
    val calory: Int,
    val title: String,
    val body: String,
    val category: String,
    val difficulty: String,
    val duration: Int,
    val createdAt: String,
    val updateAt: String
) : Parcelable