package com.devshady.newsappmultimodulekmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.devshady.newsappmultimodulekmp.ui.NewsDetailScreen
import com.devshady.newsappmultimodulekmp.ui.NewsFeedScreen
import com.devshady.newsappmultimodulekmp.ui.NewsRoute
import com.devshady.newsappmultimodulekmp.ui.NewsViewModel
import com.devshady.newsappmultimodulekmp.ui.theme.NewsTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsTheme {
                NewsApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun NewsApp(viewModel: NewsViewModel = koinViewModel()) {
    val articles by viewModel.newsState.collectAsState()
    val backStack = rememberNavBackStack(NewsRoute.Feed)
    val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>()

    NavDisplay(
        backStack = backStack,
        onBack = { if (backStack.isNotEmpty()) backStack.removeAt(backStack.size - 1) },
        sceneStrategy = listDetailStrategy,
        entryProvider = { key ->
            when (val route = key as NewsRoute) {
                is NewsRoute.Feed -> NavEntry(
                    key = route,
                    metadata = ListDetailSceneStrategy.listPane(
                        detailPlaceholder = {
                            NewsDetailScreen(article = null)
                        }
                    )
                ) {
                    NewsFeedScreen(
                        articles = articles,
                        onArticleClick = { article ->
                            backStack.removeAll { it is NewsRoute.Details }
                            backStack.add(NewsRoute.Details(article.url))
                        },
                        onRefresh = { viewModel.refresh() }
                    )
                }
                is NewsRoute.Details -> NavEntry(
                    key = route,
                    metadata = ListDetailSceneStrategy.detailPane()
                ) { detailsKey ->
                    val detailsRoute = detailsKey as NewsRoute.Details
                    val article = articles.find { it.url == detailsRoute.articleUrl }
                    NewsDetailScreen(article = article)
                }
            }
        }
    )
}
