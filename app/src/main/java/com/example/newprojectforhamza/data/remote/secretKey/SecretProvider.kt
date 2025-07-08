package com.example.newprojectforhamza.data.remote.secretKey

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecretProvider @Inject constructor() {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun getSecretNative(key: String): String
    /**----------------API KEY -----------------*/
    val apiKey: String get() = getSecretNative("API_KEY")
    /**--------------- Base URL------------------*/
    val baseUrl: String get() = getSecretNative("BASE_URL")
    /**----------- Load Images---------------*/
    val urlImg: String get() = getSecretNative("URL_IMAGE")

    val analyticsKey: String get() = getSecretNative("ANALYTICS_KEY")
}
