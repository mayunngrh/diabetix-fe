package com.example.diabetix.data.response

import com.example.diabetix.data.Report

data class ReportResponse(
    val message: String,
    val reports: List<Report>
)