package com.example.newprojectforhamza.data.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AuthModelDTO(
    @SerializedName("success")
    val success : Boolean?=false,
    @SerializedName("message")
    val message : String?="",
    @SerializedName("token_auth")
    val token : String?=""
)
