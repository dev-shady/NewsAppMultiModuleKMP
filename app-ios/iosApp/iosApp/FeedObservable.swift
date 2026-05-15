//
//  FeedObservable.swift
//  iosApp
//
//  Created by Amit Kumar on 15/05/26.
//
import SwiftUI
import SharedNewsKit

@Observable
@MainActor
class FeedObservable {
    //how shared is coming ? KoinComponent and KoinDependencies both don't have it
    private let repository: NewsRepository = KoinDependencies.shared.newsRepository
    
    // 2. SwiftUI reactive properties
    var articles: [NewsArticle] = []
    var isLoading: Bool = false
    
    init() {
        observeDatabase()
    }
    
    private func observeDatabase() {
        Task {
            do {
                for try await updatedList in repository.getNews() {
                    self.articles = updatedList
                }
            } catch {
                print("database stream closed: \(error)")
            }
        }
    }
    
    func syncNetworkFeed() async {
        isLoading = true
        do {
            _ = try await repository.refreshNews()
        } catch {
            print("Failed to sync news content: \(error.localizedDescription)")
        }
        isLoading = false
    }
}


