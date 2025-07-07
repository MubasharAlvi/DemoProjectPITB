package com.example.newprojectforhamza.presentation.utils

sealed class ResourceApiState<T>(
    val data: T? = null,
    val message: String? = null,
    val throwable: Throwable? = null
) {
    /** Loading state – can carry optional cached data for UI preview */
    class Loading<T>(data: T? = null) : ResourceApiState<T>(data)

    /** Success state – always contains non-null data */
    class Success<T>(data: T) : ResourceApiState<T>(data)

    /** Error state – can carry optional cached data + full exception */
    class Error<T>(
        message: String,
        data: T? = null,
        throwable: Throwable? = null
    ) : ResourceApiState<T>(data, message, throwable)
}

