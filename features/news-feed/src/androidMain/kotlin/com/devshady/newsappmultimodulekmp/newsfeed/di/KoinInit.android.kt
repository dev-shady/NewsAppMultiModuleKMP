package com.devshady.newsappmultimodulekmp.newsfeed.di

import com.devshady.newsappmultimodulekmp.database.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { DriverFactory(get()).createDriver() }
}
