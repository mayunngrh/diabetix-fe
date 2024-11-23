package com.example.diabetix.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodNutrition(
    val foodName: String,
    val glucose: Float,
    val levelGlycemic: String,
    val calories: Int,
    val fat: Int,
    val carbohydrate: Int,
    val indexGlycemic:Int,
    val protein:Int,
    val advice:String,
    val currentGlucose:Int,
    val maxGlucose:Int
) : Parcelable
