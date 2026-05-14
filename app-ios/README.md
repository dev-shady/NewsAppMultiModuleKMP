# app-ios

This is a placeholder for the iOS application.

## Integration with KMP

The shared business logic is provided by the `:features:news-feed` module. 
When building the project, a Swift framework is generated for iOS.

### Using SKIE

We use **SKIE** to enhance the Kotlin-Swift interoperability.
- `StateFlow` is converted to Swift `AsyncSequence`.
- `FeedStateHolder` can be used to observe the news feed state reactively.

### Example Usage (Swift)

```swift
import SwiftUI
import features_news_feed

class ObservableFeedState: ObservableObject {
    @Published var articles: [NewsArticle] = []
    private var stateHolder: FeedStateHolder = ... // Resolve via Koin or manual init

    init() {
        Task {
            for await state in stateHolder.newsState {
                self.articles = state
            }
        }
    }
}
```
