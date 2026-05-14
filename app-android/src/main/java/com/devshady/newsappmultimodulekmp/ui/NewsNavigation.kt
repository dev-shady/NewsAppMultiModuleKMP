package com.devshady.newsappmultimodulekmp.ui

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface NewsRoute : NavKey {
    @Serializable
    data object Feed : NewsRoute

    @Serializable
    data class Details(val articleUrl: String) : NewsRoute
}
