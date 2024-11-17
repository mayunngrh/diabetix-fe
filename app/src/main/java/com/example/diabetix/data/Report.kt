package com.example.diabetix.data

data class Report (
    val id: Int?,
    val userID:String?,
    val startDate:String?,
    val endDate: String?,
    val trackers: List<Tracker>?,
    val missions:List<Missions>?,
    val advice: String?,

)
