package com.example.diabetix.data.request

data class AddFoodRequest(
    val foodName: String,
    val foodImage: String,
    val glucose: Float,
    val calory: Float,
    val fat: Float,
    val protein: Float,
    val carbohydrate:Float
)
