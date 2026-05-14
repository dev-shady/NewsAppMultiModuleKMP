package com.devshady.newsappmultimodulekmp.database.di

import com.devshady.newsappmultimodulekmp.database.NewsDatabase
import org.koin.dsl.module

val coreDatabaseModule = module {
    single { NewsDatabase(get()) }
}
