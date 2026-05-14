package com.devshady.newsappmultimodulekmp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devshady.newsappmultimodulekmp.newsfeed.domain.NewsArticle
import com.devshady.newsappmultimodulekmp.newsfeed.domain.NewsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val newsState: StateFlow<List<NewsArticle>> = repository.getNews()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                repository.refreshNews()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
