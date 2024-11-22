package com.example.diabetix.data.response

import android.provider.ContactsContract.Data
import com.example.diabetix.data.Bmi
import com.google.gson.annotations.SerializedName

data class BmiResponse(
    @SerializedName("data") val data: BMIData,
    @SerializedName("message") val message: String
)

data class BMIData(
    @SerializedName("currentBMI") val currentBMI: Bmi,
    @SerializedName("weekPreviousBMI") val weekPreviousBMI: List<Bmi>,
    @SerializedName("allBMI") val allBMI: List<Bmi>
)
