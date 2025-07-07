# 🎬 NewProjectForHamza

Welcome! This is a Movies App using TMDB API.

## 🔧 Setup Instructions

1. Open the project in Android Studio.
2. Make sure you have NDK and CMake installed.
3. Add your secret keys in `native-lib.cpp`.
4. Connect a device or emulator.
5. Run the app.

## 🧪 Test Instructions

- Run unit tests: `./gradlew test`
- Run instrumented tests: `./gradlew connectedAndroidTest`

## 📦 Features

- MVVM + Clean Architecture
- Room Database
- Retrofit with Sealed Result Handling
- Native Secrets with JNI
- Use Dagger Hilt for Singleton etc.
## 🔐 Secrets

Secrets are stored in native C++ using `getSecretNative`.

> See: `native-lib.cpp`
