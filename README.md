# iTroll

Multiplatform application designed for seamless media casting to a wide range of devices i.e Chromecasts, Android TVs, Apple TVs and AirPlay devices across your local network.

This application features deep native media integration by leveraging AndroidX Media3 and CastPlayer on Android alongside the official Google Cast iOS SDK and AVKit/MediaPlayer on iOS.
It delivers a modern Material 3 experience with a unified UI that seamlessly lists both Chromecasts via the Google Cast SDK and Apple products via native AirPlay.


## Tech Stack
- **Language**: Kotlin
- **UI**: Compose Multiplatform
- **Dependency Injection**: Koin
- **Casting (Android)**: Media3 Cast
- **Casting (iOS)**: Google Cast SDK (via CocoaPods) & AirPlay

## Project Structure
- `androidApp/`: Native Android application entry point.
- `iosApp/`: Native iOS application entry point (SwiftUI).
- `shared/`: Common logic and UI shared between Android and iOS.
    - `commonMain/`: Shared interfaces, ViewModels and Compose UI.
    - `androidMain/`: Android-specific implementation using Media3.
    - `iosMain/`: iOS-specific implementation and AirPlay picker interop.

## Setup Instructions
### 1. Prerequisites
- **Android Studio** with the Kotlin Multiplatform plugin.
- **Xcode** (for iOS development).
- **CocoaPods**: Required for the iOS Google Cast SDK.
  ```bash
  brew install cocoapods
  ```

### 2. iOS Dependency Installation
Run from the project root directory to install the iOS dependencies:
```bash
./gradlew podInstall
```

[//]: # (## Development)

### Running the Apps
- **Android**: Select `androidApp` in the run configurations and click Run.
- **iOS**: Open the `.xcworkspace` or the `iosApp` directory in Xcode and deploy to a **physical device**.

**Note**:  Chromecast discovery and AirPlay will only function on physical hardware connected to the same network as your casting devices.
