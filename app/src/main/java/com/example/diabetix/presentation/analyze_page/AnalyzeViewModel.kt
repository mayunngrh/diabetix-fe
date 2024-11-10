package com.example.diabetix.presentation.analyze_result

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.data.request.AnalyzeRequest
import com.example.diabetix.data.request.LoginRequest
import com.example.diabetix.presentation.login.LoginState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class AnalyzeViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
): ViewModel()  {


    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }

    private val _nutrition = MutableStateFlow<String>("")
    val nutrition: StateFlow<String> = _nutrition


    private val TOKEN = stringPreferencesKey("token")
    private val ID = stringPreferencesKey("id")


    fun analyze(context: Context, bitmap: Bitmap) {
        _state.value = MyState.Loading
        viewModelScope.launch {
            val imageFile = withContext(Dispatchers.IO) {
                bitmapToFile(context, bitmap) // Run file creation on IO dispatcher
            }
            executeAnalysis(imageFile)
        }
    }

    private suspend fun executeAnalysis(imageFile: File) {
        try {
            val tokenValue = token.first()
            val requestFile = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("Image", imageFile.name, requestFile)

            val response = withContext(Dispatchers.IO) {
                apiService.analyze("Bearer $tokenValue", imagePart)
            }

            println("NILAI IMAGE PART: $imagePart")

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val foodNutrition = response.body()?.result

                    val gson = Gson()
                    _nutrition.value = gson.toJson(foodNutrition)

                    _state.value = MyState.Success
                } else {
                    _state.value = MyState.Error("Request failed")
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                _state.value = MyState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState(){
        _state.value = MyState.Idle
    }

    fun bitmapToFile(context: Context, bitmap: Bitmap): File {
        val file = File(context.cacheDir, "photo.jpg")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, out)
        }
        return file
    }

}
