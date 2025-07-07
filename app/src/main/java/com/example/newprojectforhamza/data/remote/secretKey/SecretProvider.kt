package com.example.newprojectforhamza.data.remote.secretKey

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecretProvider @Inject constructor() {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun getSecretNative(key: String): String

    val apiKey: String get() = getSecretNative("API_KEY")
    val baseUrl: String get() = getSecretNative("BASE_URL")
    val analyticsKey: String get() = getSecretNative("ANALYTICS_KEY")
}
