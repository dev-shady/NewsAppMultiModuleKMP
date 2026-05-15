import com.devshady.newsappmultimodulekmp.newsfeed.domain.NewsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object KoinDependencies : KoinComponent {
    val newsRepository: NewsRepository by inject()
}