package com.devshady.newsappmultimodulekmp.newsfeed.domain

import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<List<NewsArticle>>
    suspend fun refreshNews()
}

data class NewsArticle(
    val url: String,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val sourceName: String?,
    val content: String?
)
