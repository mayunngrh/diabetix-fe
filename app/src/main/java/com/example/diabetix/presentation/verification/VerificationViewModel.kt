package com.example.diabetix.presentation.verification

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.data.request.VerifyRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
): ViewModel(){

    private val _verificationState = MutableStateFlow<VerificationState>(VerificationState.Idle)
    val verificationState: StateFlow<VerificationState> = _verificationState

    val idFlow: Flow<String> = dataStore.data
        .map { preferences -> preferences[ID] ?: "" }


    private val TOKEN = stringPreferencesKey("token")
    private val ID = stringPreferencesKey("id")

    fun verify(request: VerifyRequest) {
        _verificationState.value = VerificationState.Loading
        viewModelScope.launch {
            try {
                val response = apiService.verify(request)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _verificationState.value = VerificationState.Success
                    } ?: run {
                        _verificationState.value = VerificationState.Error("Empty response body")
                    }
                } else {
                    _verificationState.value = VerificationState.Error("Register failed")
                }
            } catch (e: Exception) {
                _verificationState.value = VerificationState.Error(e.message ?: "Unknown error")
            }
        }
    }



    fun resetVerificationState(){
        _verificationState.value = VerificationState.Idle
    }

}

sealed class VerificationState{
    object Idle: VerificationState()
    object Loading: VerificationState()
    object Success: VerificationState()
    data class Error(val message:String):VerificationState()
}