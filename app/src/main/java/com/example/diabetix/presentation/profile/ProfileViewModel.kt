package com.example.diabetix.presentation.profile

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.Profile
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.presentation.analyze_result.MyState
import com.example.diabetix.presentation.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
): ViewModel()  {
    private val TOKEN = stringPreferencesKey("token")
    private val ID = stringPreferencesKey("id")

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val _user = MutableStateFlow<Profile?>(null)
    val user: StateFlow<Profile?> = _user

    init {
        fetchUserData()
    }


    fun logout(){
        viewModelScope.launch {
            clearToken()
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
                        println("response body adalah: ${response.body()!!.profiles}" )
                        _user.value = response.body()!!.profiles
                        println("user value adalah: ${_user.value}" )

                        _state.value = MyState.Success

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

    suspend fun clearToken(){
        dataStore.edit {
            it[ID] = ""
            it[TOKEN] = ""
        }
    }

    fun resetState(){
        _state.value = MyState.Idle
    }

}