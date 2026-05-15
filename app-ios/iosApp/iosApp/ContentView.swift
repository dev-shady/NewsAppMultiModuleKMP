import SwiftUI
import SharedNewsKit

struct ContentView: View {
    // Initialize our UI state driver wrapper
    @State private var observable = FeedObservable()
    
    var body: some View {
        NavigationStack {
            Group {
                if observable.articles.isEmpty && observable.isLoading {
                    VStack(spacing: 16) {
                        ProgressView()
                        Text("Loading breaking news...")
                            .font(.subheadline)
                            .foregroundColor(.secondary)
                    }
                } else {
                    List(observable.articles, id: \.url) { article in
                        VStack(alignment: .leading, spacing: 8) {
                            Text(article.title)
                                .font(.headline)
                                .foregroundColor(.primary)
                            
                            if let description = article.description_ {
                                Text(description)
                                    .font(.subheadline)
                                    .foregroundColor(.secondary)
                                    .lineLimit(2)
                            }
                        }
                        .padding(.vertical, 4)
                    }
                }
            }
            .navigationTitle("KalSeAajTak")
            // Native Apple pull-to-refresh directly calls your shared KMP business rules
            .refreshable {
                await observable.syncNetworkFeed()
            }
            // Triggers a network refresh automatically when the view appears on screen
            .task {
                await observable.syncNetworkFeed()
            }
        }
    }
}

