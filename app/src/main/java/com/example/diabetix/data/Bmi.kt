package com.example.diabetix.data

data class Bmi(
    val id:Int,
    val userID:String,
    val height: Float,
    val weight:Float,
    val status:String,
    val bmi:Float,
    val createdAt:String
)
