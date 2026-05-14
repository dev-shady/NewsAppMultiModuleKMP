package com.devshady.newsappmultimodulekmp.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class NewsApiService(val httpClient: HttpClient) {
    
    suspend inline fun <reified T> executeGet(
        endpoint: NewsEndpoint,
        params: Map<String, String> = emptyMap()
    ): T {
        return httpClient.get {
            url(BASE_URL + endpoint.path)
            parameter("apiKey", NetworkConfig.apiKey)
            params.forEach { (key, value) ->
                parameter(key, value)
            }
        }.body()
    }

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}
