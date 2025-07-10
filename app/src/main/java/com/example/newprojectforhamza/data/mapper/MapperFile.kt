package com.example.newprojectforhamza.data.mapper

import com.example.newprojectforhamza.data.remote.models.AuthModelDTO
import com.example.newprojectforhamza.data.remote.models.PopularResultDTO
import com.example.newprojectforhamza.data.remote.models.TopRatedResultDTO
import com.example.newprojectforhamza.domain.domainModels.AuthDomainModel
import com.example.newprojectforhamza.domain.domainModels.PopularMoviesModel
import com.example.newprojectforhamza.domain.domainModels.TopRatedMoviesModel

/**
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
*/

/**
 * fun PopularMoviesEntity.toPopularDomain(): PopularMoviesModel =
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
    )*/
fun PopularResultDTO.toPopularDomain(): PopularMoviesModel =
    PopularMoviesModel(
        id=id,
        title=title,
        posterPath=poster_path,
        genreIds=genreIds,
        backdropPath=backdrop_path,
        originalTitle=original_title,
        overview=overview,
        popularity=popularity,
        releaseDate=release_date,
        video = false
    )

fun TopRatedResultDTO.toTopRatedDomain(): TopRatedMoviesModel =
    TopRatedMoviesModel(
        id=id,
        title=original_name,
        posterPath=poster_path,
        genreIds=genreIds,
        backdropPath=backdrop_path,
        originalTitle=original_name,
        overview=overview,
        popularity=popularity,
        releaseDate=first_air_date,
        video=video
    )
fun AuthModelDTO.toDomain(): AuthDomainModel {
    return AuthDomainModel(
        success = this.success,
        message = this.message,
        token = this.token
    )
}
