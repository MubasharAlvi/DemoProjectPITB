package com.example.newprojectforhamza.domain.domainModels

data class ListOfAllMovies(
    val popular: List<Movie>?=emptyList(),
    val topRated: List<Movie>?=emptyList()
)