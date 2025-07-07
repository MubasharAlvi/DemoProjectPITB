package com.example.newprojectforhamza.domain.mapper

import com.example.newprojectforhamza.domain.domainModels.Movie
import com.example.newprojectforhamza.domain.domainModels.PopularMoviesModel
import com.example.newprojectforhamza.domain.domainModels.TopRatedMoviesModel

fun PopularMoviesModel.toDomain(): Movie =
    Movie(
        id           = id,
        title        = title,
        posterPath   = posterPath,
        genreIds     = genreIds,
        backdropPath = backdropPath,
        originalTitle = originalTitle,
        overview     = overview,
        releaseDate  = releaseDate,
        video        = video
    )


fun TopRatedMoviesModel.toDomain(): Movie =
    Movie(
        id           = id,
        title        = title,
        posterPath   = posterPath,
        genreIds     = genreIds,
        backdropPath = backdropPath,
        originalTitle = originalTitle?: "",
        overview     = overview,
        releaseDate  = releaseDate,
        video        = video
    )