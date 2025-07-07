package com.example.newprojectforhamza.domain.domainModels

data class PopularMoviesModel (
    val genreIds: List<Int>?=emptyList(),
    val id: Int?=0,
    val backdropPath: String?="",
    val originalLanguage: String?="",
    val originalTitle: String?="",
    val overview: String?="",
    val popularity: Double?=0.0,
    val posterPath: String?="",
    val releaseDate: String?="",
    val title: String?="",
    val video: Boolean?=false
    )