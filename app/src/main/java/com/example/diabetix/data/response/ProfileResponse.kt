package com.example.diabetix.data.response

import android.service.autofill.UserData
import com.example.diabetix.data.Profile

data class ProfileResponse(
    val message: String,
    val profiles: Profile
)