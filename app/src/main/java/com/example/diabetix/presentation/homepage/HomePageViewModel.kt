package com.example.diabetix.presentation.homepage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.remote.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
): ViewModel() {

    private val TOKEN = stringPreferencesKey("token")

    init {
        viewModelScope.launch {
            val token = getToken()
            println("TOKEN ADALAH " + token)

        }

    }
    suspend fun getToken(): Flow<String> {
        return dataStore.data
            .map { preferences ->
                preferences[TOKEN] ?: ""
            }
    }

}