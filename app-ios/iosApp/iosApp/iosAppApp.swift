//
//  iosAppApp.swift
//  iosApp
//
//  Created by Amit Kumar on 15/05/26.
//

import SwiftUI
import SharedNewsKit

@main
struct iosAppApp: App {
    init() {
        KoinInitKt.doInitKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
