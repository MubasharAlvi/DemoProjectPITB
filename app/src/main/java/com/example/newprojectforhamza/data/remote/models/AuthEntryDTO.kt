package com.example.newprojectforhamza.data.remote.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AuthEntryDTO(
    @SerializedName("message")
    val message: String = "",
    @SerializedName("token_auth")
    val tokenAuth: String = "",
    @SerializedName("data")
    val data: UserRegisterModelDTO = UserRegisterModelDTO()
)