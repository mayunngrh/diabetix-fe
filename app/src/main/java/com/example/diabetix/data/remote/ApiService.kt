package com.example.diabetix.data.remote

import com.example.diabetix.data.request.AnalyzeRequest
import com.example.diabetix.data.request.LoginRequest
import com.example.diabetix.data.request.PersonalizedRequest
import com.example.diabetix.data.request.RegisterRequest
import com.example.diabetix.data.request.VerifyRequest
import com.example.diabetix.data.response.AnalyzeResponse
import com.example.diabetix.data.response.LoginResponse
import com.example.diabetix.data.response.PersonalizedResponse
import com.example.diabetix.data.response.RegisterResponse
import com.example.diabetix.data.response.VerifyResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>


    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    @POST("auth/verify")
    suspend fun verify(
        @Body request: VerifyRequest
    ): Response<VerifyResponse>

    @POST("user/personalization")
    suspend fun personalized(
        @Body request: PersonalizedRequest
    ): Response<PersonalizedResponse>


    @Multipart
    @POST("tracker/predict")
    suspend fun analyze(
        @Header("Authorization") token: String,
        @Part foodImage: MultipartBody.Part
    ): Response<AnalyzeResponse>
}