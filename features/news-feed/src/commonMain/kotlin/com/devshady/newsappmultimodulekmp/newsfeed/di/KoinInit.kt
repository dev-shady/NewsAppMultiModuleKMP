package com.devshady.newsappmultimodulekmp.newsfeed.di

import com.devshady.newsappmultimodulekmp.database.di.coreDatabaseModule
import com.devshady.newsappmultimodulekmp.network.di.coreNetworkModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    additionalModules: List<Module> = emptyList(),
    appDeclaration: KoinAppDeclaration = {}
) {
    startKoin {
        appDeclaration()
        modules(
            coreNetworkModule,
            coreDatabaseModule,
            featureNewsFeedModule,
            platformModule,
            *additionalModules.toTypedArray()
        )
    }
}

// For iOS
fun initKoin() = initKoin {}

expect val platformModule: Module
