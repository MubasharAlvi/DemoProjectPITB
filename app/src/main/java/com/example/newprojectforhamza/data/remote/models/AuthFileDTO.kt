package com.example.newprojectforhamza.data.remote.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AuthFileDTO(
    @SerializedName("AuthMock")
    val entries: MutableList<AuthEntryDTO>
)