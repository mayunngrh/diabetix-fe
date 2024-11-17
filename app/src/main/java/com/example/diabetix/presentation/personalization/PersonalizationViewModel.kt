package com.example.diabetix.presentation.personalization

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.data.request.PersonalizedRequest
import com.example.diabetix.data.request.VerifyRequest
import com.example.diabetix.presentation.verification.VerificationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonalizationViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
): ViewModel()  {

    private val _personalizationState = MutableStateFlow<PersonalizationState>(PersonalizationState.Idle)
    val personalizationState: StateFlow<PersonalizationState> = _personalizationState


    private val ID = stringPreferencesKey("id")
    val idFlow: Flow<String> = dataStore.data
        .map { preferences -> preferences[ID] ?: "" }


    fun personalized(request: PersonalizedRequest) {
        _personalizationState.value = PersonalizationState.Loading
        viewModelScope.launch {
            try {
                val response = apiService.personalized(request)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _personalizationState.value = PersonalizationState.Success
                        clearToken()
                    } ?: run {
                        _personalizationState.value = PersonalizationState.Error("Empty response body")
                    }
                } else {
                    _personalizationState.value = PersonalizationState.Error("Update Data failed")
                }
            } catch (e: Exception) {
                _personalizationState.value = PersonalizationState.Error(e.message ?: "Unknown error")
            }
        }
    }

    suspend fun clearToken(){
        dataStore.edit {
            it[ID] = ""
        }
    }

    fun resetPersonalizationState(){
        _personalizationState.value = PersonalizationState.Idle
    }
}

sealed class PersonalizationState{
    object Idle: PersonalizationState()
    object Loading: PersonalizationState()
    object Success: PersonalizationState()
    data class Error(val message:String):PersonalizationState()
}
