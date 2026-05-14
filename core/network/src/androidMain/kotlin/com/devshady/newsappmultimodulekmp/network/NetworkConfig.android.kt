package com.devshady.newsappmultimodulekmp.network

import com.devshady.newsappmultimodulekmp.core.network.BuildConfig

actual object NetworkConfig {
    actual val apiKey: String = BuildConfig.NEWS_API_KEY
}
