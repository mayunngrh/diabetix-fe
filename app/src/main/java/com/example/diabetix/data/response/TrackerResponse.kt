package com.example.diabetix.data.response

import com.example.diabetix.data.Tracker

data class TrackerResponse(
    val message:String,
    val result: TrackerResult
)

data class TrackerResult(
    val currentTracker:Tracker,
    val sevenLatestTrackers:List<Tracker>,
    val trackers:List<Tracker>
)