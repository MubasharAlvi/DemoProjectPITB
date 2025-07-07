package com.example.newprojectforhamza.data.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TopRatedMoviesDTO(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val topRatedResultDTOs: List<TopRatedResultDTO>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)