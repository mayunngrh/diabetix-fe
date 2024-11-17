package com.example.diabetix.data

data class Profile(
    val id: String,
    val name: String,
    val email: String,
    val birth: String,
    val isActive: Boolean,
    val password: String,
    val currentExp: Int,
    val levelID: Int,
    val level: Level,
    val personalization: Personalization,
    val createdAt: String,
    val updatedAt: String
)

data class Level(
    val id: Int,
    val name: String,
    val totalExp: Int,

)

data class Personalization(
    val userID: String,
    val gender: String,
    val age: Int,
    val frequenceSport: Int,
    val maxGlucose: Int,
)
