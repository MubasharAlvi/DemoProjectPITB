package com.example.newprojectforhamza.data.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterModelDTO(
    @SerializedName("user")
    val user : String?="",
    @SerializedName("password")
    val password : String?="",
    @SerializedName("email")
    val email : String?="",
    @SerializedName("phone")
    val phone : String?=""
)