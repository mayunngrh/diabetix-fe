package com.example.diabetix.presentation.mission

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.Missions
import com.example.diabetix.data.remote.ApiService
import com.example.diabetix.presentation.analyze_result.MyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val TOKEN = stringPreferencesKey("token")

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val _missions = MutableStateFlow<List<Missions>>(emptyList())
    val missions: StateFlow<List<Missions>> = _missions

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }


    val onGoingMission: StateFlow<List<Missions>> = _missions
        .map { it.filter { mission -> !mission.isDone } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val finishedMission: StateFlow<List<Missions>> = _missions
        .map { it.filter { mission -> mission.isDone } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    init {
        fetchMission()
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
                        _missions.value = response.body()!!.missions

                        println("NILAI BODY: " + response.body()!!.missions)
                        println("NILAI MISI: " + _missions.value)
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