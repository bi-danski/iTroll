import SwiftUI
import Shared

@main
struct iOSApp: App {

    init() {
        KoinInitKt.koinInit()
        CastInitializer.shared.initialize()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}