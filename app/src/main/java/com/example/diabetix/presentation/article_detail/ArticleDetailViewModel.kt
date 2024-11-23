package com.example.diabetix.presentation.article_detail

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class ArticleDetailViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
) : ViewModel()  {

    private val TOKEN = stringPreferencesKey("token")

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    fun createLikes(ArticleId:Int) {
        _state.value = MyState.Loading
        viewModelScope.launch {
            try {

                val token = token.first()

                val response = apiService.createLikes("Bearer $token",ArticleId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _state.value = MyState.Success
                        println("BERHASIL FETCH DATA")
                    } ?: run {
                        _state.value = MyState.Error("Empty response body")
                        println("FETCH DATA GAGAL 1")
                    }
                } else {
                    _state.value = MyState.Error("Fetch USER Data failed")
                    println("FETCH DATA GAGAL 2")

                }
            } catch (e: Exception) {
                _state.value = MyState.Error(e.message ?: "Unknown error")
                println("FETCH DATA GAGAL 3")
                println("error: ${e.message}")

            }
        }
    }

    fun deleteLikes(ArticleId:Int) {
        _state.value = MyState.Loading
        viewModelScope.launch {
            try {

                val token = token.first()

                val response = apiService.deleteLikes("Bearer $token",ArticleId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _state.value = MyState.Success
                        println("BERHASIL FETCH DATA")
                    } ?: run {
                        _state.value = MyState.Error("Empty response body")
                        println("FETCH DATA GAGAL 1")
                    }
                } else {
                    _state.value = MyState.Error("Fetch USER Data failed")
                    println("FETCH DATA GAGAL 2")

                }
            } catch (e: Exception) {
                _state.value = MyState.Error(e.message ?: "Unknown error")
                println("FETCH DATA GAGAL 3")
                println("error: ${e.message}")

            }
        }
    }
    fun resetState(){
        _state.value = MyState.Idle
    }

}