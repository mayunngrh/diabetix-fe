package com.example.diabetix.data

data class Tracker(
    val id: Int,
    val userID: String,
    val totalGlucose: Int,
    val maxGlucose:Int,
    val status: String,
    val glucoseTrackerDetails: List<GlucoseTrackerDetail>,
    val createdAt:String
)