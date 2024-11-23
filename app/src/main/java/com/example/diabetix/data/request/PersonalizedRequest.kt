package com.example.diabetix.data.request

data class PersonalizedRequest(
    val userID:String,
    val gender:String,
    val age:Int,
    val frequenceSport:String,
    val diabetesInheritance: Boolean,
    val height : Int,
    val weight : Int
)