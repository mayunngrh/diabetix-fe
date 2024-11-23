package com.example.diabetix.presentation.daily_sugar

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.Tracker
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
class DailySugarViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
):ViewModel(){

    private val TOKEN = stringPreferencesKey("token")

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }

    private val _currentTracker = MutableStateFlow<Tracker?>(null)
    val currentTracker: StateFlow<Tracker?> = _currentTracker

    private val _historyTracker = MutableStateFlow<List<Tracker>?>(emptyList())
    val historyTracker: StateFlow<List<Tracker>?> = _historyTracker

    private val _sevenLatestTracker = MutableStateFlow<List<Tracker>?>(emptyList())
    val sevenLatestTracker: StateFlow<List<Tracker>?> = _sevenLatestTracker


    init{
        fetchTracker()
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
                        _historyTracker.value = response.body()?.result?.trackers
                        _sevenLatestTracker.value = response.body()?.result?.sevenLatestTrackers
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