package com.example.newprojectforhamza.data.remote.apiService

import com.example.newprojectforhamza.data.remote.models.PopularMovieDTO
import com.example.newprojectforhamza.data.remote.models.TopRatedMoviesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface ApiService {
    @GET("movie/{contentType}")
    suspend fun popularMovies(
        @Path("contentType") content: String,
        @Query("api_key") apiKey: String
    ): PopularMovieDTO

    @GET("movie/{contentType}")
    suspend fun topRatedMovies(
        @Path("contentType") content: String,
        @Query("api_key") apiKey: String
    ): TopRatedMoviesDTO

}