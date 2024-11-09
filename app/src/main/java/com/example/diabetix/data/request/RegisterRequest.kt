package com.example.diabetix.data.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val birth: String,
    val password: String,
    val confirmPassword: String,
)
