package com.devshady.newsappmultimodulekmp.ui.di

import com.devshady.newsappmultimodulekmp.ui.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { NewsViewModel(get()) }
}
