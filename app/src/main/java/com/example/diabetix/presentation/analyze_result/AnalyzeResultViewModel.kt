package com.example.diabetix.presentation.analyze_result

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.data.request.AnalyzeRequest
import com.example.diabetix.data.request.LoginRequest
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

}

sealed class MyState{
    object Idle: MyState()
    object Loading: MyState()
    object Success: MyState()
    data class Error(val message:String):MyState()
}
