package com.example.diabetix.data.response

import com.example.diabetix.data.Article
import com.example.diabetix.data.Missions

data class MissionResponse (
    val message: String,
    val missions: List<Missions>
    )