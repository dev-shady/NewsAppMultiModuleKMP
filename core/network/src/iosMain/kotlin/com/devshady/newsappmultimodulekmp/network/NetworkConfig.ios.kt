package com.devshady.newsappmultimodulekmp.network

import com.devshady.newsappmultimodulekmp.BuildKonfig

actual object NetworkConfig {
    actual val apiKey: String = BuildKonfig.NEWS_API_KEY // iOS would load this from Plist or build settings in a real app
}
