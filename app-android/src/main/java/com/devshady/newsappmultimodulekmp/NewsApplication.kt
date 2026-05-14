package com.devshady.newsappmultimodulekmp

import android.app.Application
import com.devshady.newsappmultimodulekmp.newsfeed.di.initKoin
import com.devshady.newsappmultimodulekmp.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            additionalModules = listOf(appModule),
            appDeclaration = {
                androidLogger()
                androidContext(this@NewsApplication)
            }
        )
    }
}
