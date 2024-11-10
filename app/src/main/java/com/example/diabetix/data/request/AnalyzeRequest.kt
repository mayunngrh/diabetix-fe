package com.example.diabetix.data.request

import okhttp3.MultipartBody

data class AnalyzeRequest(
    val image: MultipartBody.Part
)
