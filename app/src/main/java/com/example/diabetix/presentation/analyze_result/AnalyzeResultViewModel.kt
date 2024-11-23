package com.example.diabetix.presentation.analyze_result

import android.content.Context
import android.graphics.Bitmap
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.FoodNutrition
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.data.request.AddFoodRequest
import com.example.diabetix.data.request.AnalyzeRequest
import com.example.diabetix.data.request.LoginRequest
import com.example.diabetix.presentation.login.LoginState
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
class AnalyzeResultViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
): ViewModel()  {


    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }


    private val TOKEN = stringPreferencesKey("token")
    private val ID = stringPreferencesKey("id")


    fun resetState(){
        _state.value = MyState.Idle
    }

    fun addFood(nutrition:FoodNutrition,context: Context, imageFile: Bitmap) {
        _state.value = MyState.Loading
        viewModelScope.launch {
            try {
                val tokenValue = token.first()
                val imageFile = withContext(Dispatchers.IO) {
                    bitmapToFile(context, imageFile)
                }

                val requestFile = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imagePart = MultipartBody.Part.createFormData("file", imageFile.name, requestFile)

                val response = apiService.uploadFiles("Bearer $tokenValue",imagePart)
                if (response.isSuccessful) {
                    response.body()?.let {
                        println("CHECK KONDISI LOGIN: 1")
                        val request = AddFoodRequest(
                            foodName = nutrition.foodName,
                            foodImage = response.body()!!.url,
                            glucose = nutrition.glucose.toInt(),
                            calory = nutrition.calories,
                            fat = nutrition.fat,
                            protein = nutrition.protein,
                            carbohydrate = nutrition.carbohydrate,
                            indexGlycemic = nutrition.indexGlycemic
                        )
                        val response2 = apiService.addFood("Bearer $tokenValue",request)
                        _state.value = MyState.Success

                    } ?: run {
                        println("CHECK KONDISI LOGIN: 2")
                        _state.value = MyState.Error("Empty response body")
                    }
                } else {
                    println("CHECK KONDISI LOGIN: 3")
                    _state.value = MyState.Error("Periksa Kembali Jaringan Anda")
                }
            } catch (e: Exception) {
                println("CHECK KONDISI LOGIN: 4")
                _state.value = MyState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun bitmapToFile(context: Context, bitmap: Bitmap): File {
        val file = File(context.cacheDir, "photo.jpg")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, out)
        }
        return file
    }

}

sealed class MyState{
    object Idle: MyState()
    object Loading: MyState()
    object Success: MyState()
    data class Error(val message:String):MyState()
}
