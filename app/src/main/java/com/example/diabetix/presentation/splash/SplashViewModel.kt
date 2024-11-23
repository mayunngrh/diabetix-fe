package com.example.diabetix.presentation.splash

import DataStoreHelper
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.Profile
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.presentation.analyze_result.MyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>,
): ViewModel() {

    private val _isLogin = MutableStateFlow(false)
    val isLogin: StateFlow<Boolean> = _isLogin

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val _user = MutableStateFlow<Profile?>(null)
    val user: StateFlow<Profile?> = _user

    private val TOKEN = stringPreferencesKey("token")

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }

    init {
        viewModelScope.launch {
            checkLogin().collect{
                _isLogin.value = it
                if(_isLogin.value){
                    fetchUserData()
                }
            }

        }
    }

    private val ID = stringPreferencesKey("id")

    private fun checkLogin(): Flow<Boolean> {
        return dataStore.data.map {
            it[TOKEN]?.isNotEmpty() ?: false
        }
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
                        println("KONDISI NYA ADALAH: BERHASIL")

                    } ?: run {
                        _state.value = MyState.Error("Empty response body")
                        println("KONDISI NYA ADALAH: gagal 1")

                    }
                } else {

                    _state.value = MyState.Error("Fetch USER Data failed")
                    println("KONDISI NYA ADALAH: gagal 2")

                }
            } catch (e: Exception) {
                _state.value = MyState.Error(e.message ?: "Unknown error")
                println("KONDISI NYA ADALAH: ${e.message}")
            }
        }
    }
}