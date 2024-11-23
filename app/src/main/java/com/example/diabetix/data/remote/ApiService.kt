package com.example.diabetix.data.remote

import com.example.diabetix.data.request.AddFoodRequest
import com.example.diabetix.data.request.AnalyzeRequest
import com.example.diabetix.data.request.LoginRequest
import com.example.diabetix.data.request.PersonalizedRequest
import com.example.diabetix.data.request.RegisterRequest
import com.example.diabetix.data.request.UpdateMissionRequest
import com.example.diabetix.data.request.VerifyRequest
import com.example.diabetix.data.response.AddFoodResponse
import com.example.diabetix.data.response.AnalyzeResponse
import com.example.diabetix.data.response.ArticlesResponse
import com.example.diabetix.data.response.BmiResponse
import com.example.diabetix.data.response.FileUploadResponse
import com.example.diabetix.data.response.LoginResponse
import com.example.diabetix.data.response.MissionResponse
import com.example.diabetix.data.response.PersonalizedResponse
import com.example.diabetix.data.response.ProfileResponse
import com.example.diabetix.data.response.RegisterResponse
import com.example.diabetix.data.response.ReportResponse
import com.example.diabetix.data.response.TrackerResponse
import com.example.diabetix.data.response.UpdateMissionResponse
import com.example.diabetix.data.response.VerifyResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @POST("auths/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>


    @POST("auths/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    @POST("auths/verify")
    suspend fun verify(
        @Body request: VerifyRequest
    ): Response<VerifyResponse>

    @POST("users/personalization")
    suspend fun personalized(
        @Body request: PersonalizedRequest
    ): Response<PersonalizedResponse>


    @Multipart
    @POST("trackers/predict")
    suspend fun analyze(
        @Header("Authorization") token: String,
        @Part foodImage: MultipartBody.Part
    ): Response<AnalyzeResponse>

    @GET("articles")
    suspend fun getAllArticles(
        @Header("Authorization") token: String,
    ): Response<ArticlesResponse>

    @GET("missions")
    suspend fun getAllMisions(
        @Header("Authorization") token: String,
    ): Response<MissionResponse>

    @GET("trackers")
    suspend fun getAllTrackers(
        @Header("Authorization") token: String,
    ): Response<TrackerResponse>

    @GET("users/profile")
    suspend fun getUserData(
        @Header("Authorization") token: String,
    ): Response<ProfileResponse>

    @GET("reports")
    suspend fun getReport(
        @Header("Authorization") token: String,
    ): Response<ReportResponse>

    @Multipart
    @POST("uploads/files")
    suspend fun uploadFiles(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Response<FileUploadResponse>

    @POST("trackers/add")
    suspend fun addFood(
        @Header("Authorization") token: String,
        @Body request: AddFoodRequest
    ): Response<AddFoodResponse>

    @PATCH("missions/{missionId}/accepted")
    suspend fun updateMissionStatus(
        @Header("Authorization") token: String,
        @Path("missionId") missionId: Int,
    ): Response<UpdateMissionResponse>


    @GET("bmis")
    suspend fun getAllBmi(
        @Header("Authorization") token: String,
    ): Response<BmiResponse>


    @POST("articles/{ArticleId}/likes")
    suspend fun createLikes(
        @Header("Authorization") token: String,
        @Path("ArticleId") articleId: Int
    ): Response<Unit>

    @DELETE("articles/{ArticleId}/likes")
    suspend fun deleteLikes(
        @Header("Authorization") token: String,
        @Path("ArticleId") articleId: Int
    ): Response<Unit>


}