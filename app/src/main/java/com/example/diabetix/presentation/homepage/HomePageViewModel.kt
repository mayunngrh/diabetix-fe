package com.example.diabetix.presentation.homepage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.Article
import com.example.diabetix.data.Missions
import com.example.diabetix.data.Profile
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.data.request.LoginRequest
import com.example.diabetix.presentation.analyze_result.MyState
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
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val TOKEN = stringPreferencesKey("token")

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _missions = MutableStateFlow<List<Missions>>(emptyList())
    val missions: StateFlow<List<Missions>> = _missions

    private val _user = MutableStateFlow<Profile?>(null)
    val user: StateFlow<Profile?> = _user

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }


    init {
        fetchArticle()
        fetchMission()
        fetchUserData()
    }

    private fun fetchUserData() {
        _state.value = MyState.Loading
        viewModelScope.launch {
            try {

                val token = token.first()

                val response = apiService.getUserData("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let {
                        _state.value = MyState.Success
                        _user.value = response.body()!!.profiles
                    } ?: run {
                        _state.value = MyState.Error("Empty response body")
                    }
                } else {

                    _state.value = MyState.Error("Fetch USER Data failed")
                }
            } catch (e: Exception) {
                _state.value = MyState.Error(e.message ?: "Unknown error")
            }
        }
    }


    private fun fetchArticle() {
        _state.value = MyState.Loading // Set loading state
        viewModelScope.launch {
            try {

                val token = token.first()
                println("TOKEN ADALAH " + token)

                val response = apiService.getAllArticles("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let {
                        _state.value = MyState.Success
                        _articles.value = response.body()!!.articles
                    } ?: run {
                        _state.value = MyState.Error("Empty response body")
                    }
                } else {

                    _state.value = MyState.Error("Fetch Data failed")
                }
            } catch (e: Exception) {
                println("CHECK KONDISI LOGIN: 4")
                _state.value = MyState.Error(e.message ?: "Unknown error")
            }
        }
    }


    private fun fetchMission() {
        _state.value = MyState.Loading // Set loading state
        viewModelScope.launch {
            try {
                val token = token.first()
                val response = apiService.getAllMisions("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let {
                        _state.value = MyState.Success
                        _missions.value = response.body()!!.missions

                        println("NILAI BODY: " + response.body()!!.missions)
                        println("NILAI MISI: " + _missions.value)
                    } ?: run {
                        _state.value = MyState.Error("Empty response body")
                    }
                } else {

                    _state.value = MyState.Error("Fetch Data failed")
                }
            } catch (e: Exception) {
                println("CHECK KONDISI LOGIN: 4")
                _state.value = MyState.Error(e.message ?: "Unknown error")
            }
        }
    }



}



