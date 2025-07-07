package com.example.newprojectforhamza.domain.domainModels

data class Movie(
    val id: Int?=0,
    val title: String?="",
    val posterPath: String?="",
    val genreIds: List<Int>?=emptyList(),
    val backdropPath: String?="",
    val originalTitle: String?="",
    val overview: String?="",
    val popularity: Double=0.0,
    val releaseDate: String?="",
    val video: Boolean?=false
)


