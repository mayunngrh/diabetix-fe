package com.example.diabetix.data.response


data class LoginResponse(
    val message:String,
    val response:SingleLoginResponse
)

data class SingleLoginResponse(
    val id:String,
    val token:String
)