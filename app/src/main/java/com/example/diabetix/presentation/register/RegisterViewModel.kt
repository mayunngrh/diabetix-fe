package com.example.diabetix.presentation.register

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.data.request.RegisterRequest
import com.example.diabetix.data.response.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel  @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
): ViewModel() {


    private val _registerResult = MutableLiveData<Result<RegisterResponse>>()
    val registerResult: LiveData<Result<RegisterResponse>> = _registerResult

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    private val TOKEN = stringPreferencesKey("token")
    private val ID = stringPreferencesKey("id")


    fun register(request: RegisterRequest) {
        _registerState.value = RegisterState.Loading // Set loading state
        viewModelScope.launch {
            try {
                val response = apiService.register(request)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _registerState.value = RegisterState.Success
                        saveToken("",response.body()!!.id)
                    } ?: run {

                        _registerState.value = RegisterState.Error("Empty response body")
                    }
                } else {
                    _registerState.value = RegisterState.Error("Register failed")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(e.message ?: "Unknown error")
            }
        }
    }

    suspend fun saveToken(token:String, id:String){
        dataStore.edit {
            it[TOKEN] = token
            it[ID] = id
        }
    }

    fun formatDate(date: Date?): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return if (date != null) {
            dateFormat.format(date)
        } else {
            ""
        }
    }

    fun resetRegisterState(){
        _registerState.value = RegisterState.Idle
    }

}

sealed class RegisterState{
    object Idle: RegisterState()
    object Loading: RegisterState()
    object Success: RegisterState()
    data class Error(val message:String):RegisterState()
}