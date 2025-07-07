package com.example.newprojectforhamza.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.newprojectforhamza.data.local.entity.TopRatedMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopRatedMovieDao : BaseDao<TopRatedMoviesEntity> {

    @Query("DELETE FROM topRatedMovies")
    suspend fun clear()

    @Query("SELECT * FROM topRatedMovies ORDER BY voteAverage DESC")
    fun observeTopRated(): Flow<List<TopRatedMoviesEntity>>

    @Query("SELECT * FROM topRatedMovies ORDER BY popularity DESC")
    fun observePopular(): Flow<List<TopRatedMoviesEntity>>

    @Query("SELECT * FROM topRatedMovies ORDER BY voteAverage DESC")
    fun pagingSource(): PagingSource<Int, TopRatedMoviesEntity>
}
