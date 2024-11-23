package com.example.diabetix.presentation.homepage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.Article
import com.example.diabetix.data.Bmi
import com.example.diabetix.data.Missions
import com.example.diabetix.data.Profile
import com.example.diabetix.data.Tracker
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

    private val _currentBmi = MutableStateFlow<Bmi?>(null)
    val currentBmi: StateFlow<Bmi?> = _currentBmi

    private val _currentTracker = MutableStateFlow<Tracker?>(null)
    val currentTracker: StateFlow<Tracker?> = _currentTracker

    private val _user = MutableStateFlow<Profile?>(null)
    val user: StateFlow<Profile?> = _user

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }


    init {
        fetchArticle()
        fetchMission()
        fetchUserData()
        fetchBmi()
        fetchTracker()
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
                        println("BERHASIL FETCH DATA")
                    } ?: run {
                        _state.value = MyState.Error("Empty response body")
                        println("FETCH DATA GAGAL 1")
                    }
                } else {
                    _state.value = MyState.Error("Fetch USER Data failed")
                    println("FETCH DATA GAGAL 2")

                }
            } catch (e: Exception) {
                _state.value = MyState.Error(e.message ?: "Unknown error")
                println("FETCH DATA GAGAL 3")
                println("error: ${e.message}")

            }
        }
    }


    private fun fetchArticle() {
        _state.value = MyState.Loading // Set loading state
        viewModelScope.launch {
            try {

                val token = token.first()

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
                        _missions.value = response.body()!!.missions.filter { !it.isDone }

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

    private fun fetchBmi() {
        _state.value = MyState.Loading
        viewModelScope.launch {
            try {
                val token = token.first()
                val response = apiService.getAllBmi("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let {
                        _state.value = MyState.Success
                        _currentBmi.value = response.body()?.data?.currentBMI

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

    private fun fetchTracker() {
        _state.value = MyState.Loading
        viewModelScope.launch {
            try {
                val token = token.first()
                val response = apiService.getAllTrackers("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let {
                        _state.value = MyState.Success
                        _currentTracker.value = response.body()?.result?.currentTracker
                        print("KONDISI TRACKER BERHASIL")
                    } ?: run {
                        _state.value = MyState.Error("Empty response body")
                        print("KONDISI TRACKER GAGAL1")
                    }
                } else {
                    print("KONDISI TRACKER GAGAL2")
                    _state.value = MyState.Error("Fetch Data failed")
                }
            } catch (e: Exception) {
                print("KONDISI TRACKER GAGAL3: ${e.message}")
                _state.value = MyState.Error(e.message ?: "Unknown error")
            }
        }
    }



}



