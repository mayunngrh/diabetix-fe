package com.example.diabetix.data.response

import com.example.diabetix.data.FoodNutrition

data class AnalyzeResponse(
    val message:String,
    val result: FoodNutrition
)