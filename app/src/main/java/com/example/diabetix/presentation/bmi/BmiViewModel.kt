package com.example.diabetix.presentation.bmi

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.Bmi
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.presentation.analyze_result.MyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val TOKEN = stringPreferencesKey("token")

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val _currentBmi = MutableStateFlow<Bmi?>(null)
    val currentBmi: StateFlow<Bmi?> = _currentBmi

    private val _historyBmi = MutableStateFlow<List<Bmi>?>(emptyList())
    val historyBmi : StateFlow<List<Bmi>?> = _historyBmi

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }


    init{
        fetchBmi()
    }

    private fun fetchBmi() {
        _state.value = MyState.Loading // Set loading state
        viewModelScope.launch {
            try {
                val token = token.first()
                val response = apiService.getAllBmi("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let {
                        _state.value = MyState.Success
                        _currentBmi.value = response.body()?.data?.currentBMI
                        _historyBmi.value = response.body()?.data?.weekPreviousBMI

                        println("NILAI CURRENT BMI VIEWMODEL: ${_currentBmi.value}" )

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