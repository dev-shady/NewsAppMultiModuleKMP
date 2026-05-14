package com.devshady.newsappmultimodulekmp.network

sealed class NewsEndpoint(val path: String) {
    object TopHeadlines : NewsEndpoint("top-headlines")
    object Everything : NewsEndpoint("everything")
}
