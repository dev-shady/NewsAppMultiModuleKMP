package com.devshady.newsappmultimodulekmp.newsfeed.presentation

import com.devshady.newsappmultimodulekmp.newsfeed.domain.NewsArticle
import com.devshady.newsappmultimodulekmp.newsfeed.domain.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * A helper class for iOS to observe news feed state and trigger refreshes.
 * This class is enhanced by SKIE to provide Swift-friendly Flow support.
 */
class FeedStateHolder(private val repository: NewsRepository) {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    val newsState: StateFlow<List<NewsArticle>> = repository.getNews()
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun refresh() {
        scope.launch {
            try {
                repository.refreshNews()
            } catch (e: Exception) {
                // Handle error (e.g., log or update a UI error state)
            }
        }
    }
}
