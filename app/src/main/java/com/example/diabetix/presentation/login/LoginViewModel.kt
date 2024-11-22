package com.example.diabetix.presentation.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.data.request.LoginRequest
import com.example.diabetix.data.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
):ViewModel() {

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val TOKEN = stringPreferencesKey("token")
    private val ID = stringPreferencesKey("id")



    fun login(request: LoginRequest) {
        _loginState.value = LoginState.Loading // Set loading state
        viewModelScope.launch {
            try {
                val response = apiService.login(request)
                if (response.isSuccessful) {
                    response.body()?.let {
                        println("CHECK KONDISI LOGIN: 1")
                        _loginState.value = LoginState.Success
                        saveToken(response.body()!!.token,"")
                    // Set success state with response
                    } ?: run {
                        println("CHECK KONDISI LOGIN: 2")
                        _loginState.value = LoginState.Error("Empty response body")
                    }
                } else {
                    println("CHECK KONDISI LOGIN: 3")
                    _loginState.value = LoginState.Error("Login failed")
                }
            } catch (e: Exception) {
                println("CHECK KONDISI LOGIN: 4")
                _loginState.value = LoginState.Error(e.message ?: "Unknown error")
            }
        }
    }

    suspend fun saveToken(token:String, id:String){
        dataStore.edit {
            it[TOKEN] = token
            it[ID] = id
        }
    }

    fun resetLoginState(){
        _loginState.value = LoginState.Idle
    }
}

sealed class LoginState{
    object Idle: LoginState()
    object Loading: LoginState()
    object Success: LoginState()
    data class Error(val message:String):LoginState()
}