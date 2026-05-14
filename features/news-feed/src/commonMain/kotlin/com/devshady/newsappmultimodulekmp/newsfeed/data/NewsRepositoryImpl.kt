package com.devshady.newsappmultimodulekmp.newsfeed.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.devshady.newsappmultimodulekmp.database.NewsDatabase
import com.devshady.newsappmultimodulekmp.network.NewsApiService
import com.devshady.newsappmultimodulekmp.network.NewsEndpoint
import com.devshady.newsappmultimodulekmp.network.model.NewsResponse
import com.devshady.newsappmultimodulekmp.newsfeed.domain.NewsArticle
import com.devshady.newsappmultimodulekmp.newsfeed.domain.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(
    private val apiService: NewsApiService,
    private val database: NewsDatabase
) : NewsRepository {

    override fun getNews(): Flow<List<NewsArticle>> {
        return database.newsDatabaseQueries.getAllArticles()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { articles ->
                articles.map {
                    NewsArticle(
                        url = it.url,
                        title = it.title,
                        description = it.description,
                        urlToImage = it.urlToImage,
                        publishedAt = it.publishedAt,
                        sourceName = it.sourceName,
                        content = it.content
                    )
                }
            }
    }

    override suspend fun refreshNews() {
        withContext(Dispatchers.IO) {
            val response: NewsResponse = apiService.executeGet(
                endpoint = NewsEndpoint.TopHeadlines,
                params = mapOf("country" to "us")
            )
            
            database.newsDatabaseQueries.transaction {
                database.newsDatabaseQueries.deleteAllArticles()
                response.articles.forEach { article ->
                    database.newsDatabaseQueries.insertArticle(
                        url = article.url,
                        title = article.title,
                        description = article.description,
                        urlToImage = article.urlToImage,
                        publishedAt = article.publishedAt,
                        sourceName = article.source?.name,
                        content = article.content
                    )
                }
            }
        }
    }
}
