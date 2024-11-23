package com.example.diabetix.data.request

data class AddFoodRequest(
    val foodName: String,
    val foodImage: String,
    val glucose: Int,
    val calory: Int,
    val fat: Int,
    val protein: Int,
    val carbohydrate:Int,
    val indexGlycemic:Int
)
