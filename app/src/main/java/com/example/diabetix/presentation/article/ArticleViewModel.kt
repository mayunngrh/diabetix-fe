package com.example.diabetix.presentation.article

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetix.data.Article
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
class ArticleViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val TOKEN = stringPreferencesKey("token")

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    val token: Flow<String> = dataStore.data
        .map { preferences -> preferences[TOKEN] ?: "" }

    init {
        fetchArticle()
    }

    private fun fetchArticle() {
        _state.value = MyState.Loading // Set loading state
        viewModelScope.launch {
            try {

                val token = token.first()
                println("TOKEN ADALAH " + token)

                val response = apiService.getAllArticles("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let {
                        _state.value = MyState.Success
                        _articles.value = response.body()!!.articles
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