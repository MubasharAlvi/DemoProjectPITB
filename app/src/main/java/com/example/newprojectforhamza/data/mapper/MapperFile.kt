package com.example.newprojectforhamza.data.mapper

import com.example.newprojectforhamza.data.local.entity.PopularMoviesEntity
import com.example.newprojectforhamza.data.local.entity.TopRatedMoviesEntity
import com.example.newprojectforhamza.data.remote.models.PopularResultDTO
import com.example.newprojectforhamza.data.remote.models.TopRatedResultDTO
import com.example.newprojectforhamza.domain.domainModels.PopularMoviesModel
import com.example.newprojectforhamza.domain.domainModels.TopRatedMoviesModel
fun PopularResultDTO.toEntity(): PopularMoviesEntity = PopularMoviesEntity(
    adult            = adult,
    backdropPath     = backdropPath,
    genreIds         = genreIds,
    id               = id,
    originalLanguage = originalLanguage,
    originalTitle    = originalTitle,
    overview         = overview,
    popularity       = popularity,
    posterPath       = posterPath,
    releaseDate      = releaseDate,
    title            = title,
    video            = video,
    voteAverage      = voteAverage,
    voteCount        = voteCount
)

fun TopRatedResultDTO.toEntity() = TopRatedMoviesEntity(
    adult            = adult,
    backdropPath     = backdropPath ?: "",
    genreIds         = genreIds,
    id               = id,
    originalLanguage = originalLanguage ?: "",
    originalTitle    = originalName ?: "",
    overview         = overview ?: "",
    popularity       = popularity ?: 0.0,
    posterPath       = posterPath ?: "",
    releaseDate      = firstAirDate ?: "",
    title            = name ?: "",
    video            = false,
    voteAverage      = voteAverage ?: 0.0,
    voteCount        = voteCount ?: 0
)

fun PopularMoviesEntity.toPopularDomain(): PopularMoviesModel =
    PopularMoviesModel(
        id=id,
        title=title,
        posterPath=posterPath,
        genreIds=genreIds,
        backdropPath=backdropPath,
        originalTitle=originalTitle,
        overview=overview,
        popularity=popularity,
        releaseDate=releaseDate,
        video = false
    )

fun TopRatedMoviesEntity.toTopRatedDomain(): TopRatedMoviesModel =
    TopRatedMoviesModel(
        id=id,
        title=title,
        posterPath=posterPath,
        genreIds=genreIds,
        backdropPath=backdropPath,
        originalTitle=originalTitle,
        overview=overview,
        popularity=popularity,
        releaseDate=releaseDate,
        video=video
    )

