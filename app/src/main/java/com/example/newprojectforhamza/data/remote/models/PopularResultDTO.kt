package com.example.newprojectforhamza.data.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

//@Serializable
//data class PopularResultDTO(
//    @SerializedName("adult")
//    val adult: Boolean,
//    @SerializedName("backdrop_path")
//    val backdropPath: String,
//    @SerializedName("genre_ids")
//    val genreIds: List<Int>,
//    @SerializedName("id")
//    val id: Int,
//    @SerializedName("original_language")
//    val originalLanguage: String,
//    @SerializedName("original_title")
//    val originalTitle: String,
//    @SerializedName("overview")
//    val overview: String,
//    @SerializedName("popularity")
//    val popularity: Double,
//    @SerializedName("poster_path")
//    val posterPath: String,
//    @SerializedName("release_date")
//    val releaseDate: String,
//    @SerializedName("title")
//    val title: String,
//    @SerializedName("video")
//    val video: Boolean,
//    @SerializedName("vote_average")
//    val voteAverage: Double,
//    @SerializedName("vote_count")
//    val voteCount: Int
//)

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.newprojectforhamza.data.utils.GenreIntListConverter

/**
 * Popular movies coming from /popular endpoint.
 * The exact field names already match the JSON, so no extra @SerializedName
 * is needed as long as you use Moshi/Kotlinx serialization with matching names.
 */
@Entity(tableName = "popularMovies")
@TypeConverters(GenreIntListConverter::class)
data class PopularResultDTO(
    val adult: Boolean,
    val backdrop_path: String?,
    val genreIds: List<Int>?=emptyList(),
    @PrimaryKey val id: Int,
    val originalLanguage: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean = false,
    val voteAverage: Double?,
    val voteCount: Int?
)