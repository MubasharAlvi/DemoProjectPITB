package com.example.newprojectforhamza.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.newprojectforhamza.data.utils.Converters

@Entity(tableName = "topRatedMovies")
data class TopRatedMoviesEntity(
    val adult: Boolean,
    val backdropPath: String?,
    @TypeConverters(Converters::class)
    val genreIds: List<Int>,
    @PrimaryKey val id: Int,
    val originalLanguage: String?,
    val originalTitle: String?="",
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean,
    val voteAverage: Double?,
    val voteCount: Int?
)
