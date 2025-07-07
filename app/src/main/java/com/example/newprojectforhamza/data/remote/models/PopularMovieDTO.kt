package com.example.newprojectforhamza.data.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMovieDTO(
    @SerializedName("page")
    val pageNumber: Int,
    @SerializedName("results")
    val resultsMovie: List<PopularResultDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)