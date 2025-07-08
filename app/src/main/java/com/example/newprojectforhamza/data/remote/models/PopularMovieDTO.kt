package com.example.newprojectforhamza.data.remote.models

data class PopularMovieDTO(
    val page: Int,
    val results: List<PopularResultDTO>,
    val total_pages: Int,
    val total_results: Int
)