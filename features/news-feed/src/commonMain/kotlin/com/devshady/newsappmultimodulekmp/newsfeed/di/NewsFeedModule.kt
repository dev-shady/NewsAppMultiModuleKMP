package com.devshady.newsappmultimodulekmp.newsfeed.di

import com.devshady.newsappmultimodulekmp.newsfeed.data.NewsRepositoryImpl
import com.devshady.newsappmultimodulekmp.newsfeed.domain.NewsRepository
import com.devshady.newsappmultimodulekmp.newsfeed.presentation.FeedStateHolder
import org.koin.dsl.module

val featureNewsFeedModule = module {
    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
    factory { FeedStateHolder(get()) }
}
