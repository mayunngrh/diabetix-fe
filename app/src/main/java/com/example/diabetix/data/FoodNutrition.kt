package com.example.diabetix.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodNutrition(
    val id: Int = 0,
    val message: String,
    val foodName: String,
    val glucose: Float,
    val levelGlucose: String,
    val calories: Float,
    val fat: Float,
    val carbohydrate: Float,
    val protein: Float,
    val advice: String,
    val currentGlucose: Float,
    val maxGlucose: Float,
    val foodImage:String = ""
) : Parcelable
