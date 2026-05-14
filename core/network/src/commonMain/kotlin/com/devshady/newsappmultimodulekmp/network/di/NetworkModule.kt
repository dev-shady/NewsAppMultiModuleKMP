package com.devshady.newsappmultimodulekmp.network.di

import com.devshady.newsappmultimodulekmp.network.HttpClientFactory
import com.devshady.newsappmultimodulekmp.network.NewsApiService
import org.koin.dsl.module

val coreNetworkModule = module {
    single { HttpClientFactory.create() }
    single { NewsApiService(get()) }
}
