package com.example.diabetix.presentation.profile

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.remote.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
): ViewModel()  {
    private val TOKEN = stringPreferencesKey("token")
    private val ID = stringPreferencesKey("id")

    fun logout(){
        viewModelScope.launch {
            clearToken()

        }
    }

    suspend fun clearToken(){
        dataStore.edit {
            it[ID] = ""
            it[TOKEN] = ""
        }
    }

}