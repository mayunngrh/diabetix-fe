package com.example.diabetix.data

data class Tracker(
    val id: Int,
    val userId: String,
    val totalGlucose: Int,
    val status: String,
    val glucoseTrackerDetails: List<GlucoseTrackerDetail>
)