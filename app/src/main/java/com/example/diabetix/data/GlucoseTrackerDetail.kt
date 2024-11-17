package com.example.diabetix.data

data class GlucoseTrackerDetail(
    val id: Int,
    val trackerID: Int,
    val foodImage: String,
    val foodName: String,
    val glucose: Float,
    val calory: Float,
    val fat: Float,
    val protein: Float,
    val carbohydrate: Float,
)
