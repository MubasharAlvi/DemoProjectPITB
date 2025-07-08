package com.example.newprojectforhamza.data.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

//@Serializable
//data class TopRatedResultDTO(
//    @SerializedName("adult")
//    val adult: Boolean,
//    @SerializedName("backdrop_path")
//    val backdropPath: String?,
//    @SerializedName("first_air_date")
//    val firstAirDate: String?,
//    @SerializedName("genre_ids")
//    val genreIds: List<Int>,
//    @SerializedName("id")
//    val id: Int,
//    @SerializedName("name")
//    val name: String,
//    @SerializedName("origin_country")
//    val originCountry: List<String>,
//    @SerializedName("original_language")
//    val originalLanguage: String,
//    @SerializedName("original_name")
//    val originalName: String,
//    @SerializedName("overview")
//    val overview: String,
//    @SerializedName("popularity")
//    val popularity: Double,
//    @SerializedName("poster_path")
//    val posterPath: String?,
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
@Entity(tableName = "topRatedMovies")
@TypeConverters(GenreIntListConverter::class)
data class TopRatedResultDTO(
    val adult: Boolean,
    val backdrop_path: String?,
    val genreIds: List<Int>?=emptyList(),
    @PrimaryKey val id: Int,
    val originalLanguage: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val first_air_date: String?,
    val name: String?,
    val video: Boolean = false,
    val voteAverage: Double?,
    val voteCount: Int?
)

