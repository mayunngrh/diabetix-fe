package com.example.diabetix.presentation.splash

import DataStoreHelper
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
): ViewModel() {

    private val _isLogin = MutableStateFlow(false)
    val isLogin: StateFlow<Boolean> = _isLogin

    init {
        viewModelScope.launch {
            checkLogin().collect{
                _isLogin.value = it
            }
        }
    }

    private val ID = stringPreferencesKey("id")

    private fun checkLogin(): Flow<Boolean> {
        return dataStore.data.map {
            it[ID]?.isNotEmpty() ?: false
        }
    }
}