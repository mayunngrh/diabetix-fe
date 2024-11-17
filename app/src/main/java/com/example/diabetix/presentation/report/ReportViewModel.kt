package com.example.diabetix.presentation.report

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.Profile
import com.example.diabetix.data.Report
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
class ReportViewModel  @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
): ViewModel()  {

    private val TOKEN = stringPreferencesKey("token")
    private val ID = stringPreferencesKey("id")

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val _report = MutableStateFlow<Report?>(null)
    val report: StateFlow<Report?> = _report

    init {
        fetchReportData()
    }

    fun fetchReportData() {
        _state.value = MyState.Loading
        viewModelScope.launch {
            try {

                val token = token.first()

                val response = apiService.getReport("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let {
                        val reports = response.body()!!.reports[0]
                        _report.value = reports
                        _state.value = MyState.Success
                        println("NILAI REPORT & KAWAN2: ${_report.value!!}")
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

    fun resetState(){
        _state.value = MyState.Idle
    }


}