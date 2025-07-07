package com.example.newprojectforhamza.data.repository

import com.example.newprojectforhamza.domain.domainModels.PopularMoviesModel
import com.example.newprojectforhamza.domain.domainModels.TopRatedMoviesModel
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
  suspend fun getPopularMovies(): Flow<ResourceApiState<List<PopularMoviesModel>>>
   suspend fun getTopRatedMovies(): Flow<ResourceApiState<List<TopRatedMoviesModel>>>
}