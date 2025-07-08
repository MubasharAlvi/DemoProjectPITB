package com.example.newprojectforhamza.data.remote.models

data class TopRatedMoviesDTO(
   val page: Int,
   val results: List<TopRatedResultDTO>,
    val total_pages: Int,
  val total_results: Int
)