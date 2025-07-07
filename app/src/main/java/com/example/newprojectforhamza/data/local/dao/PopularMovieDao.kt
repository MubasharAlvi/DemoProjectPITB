package com.example.newprojectforhamza.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.newprojectforhamza.data.local.entity.PopularMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMovieDao : BaseDao<PopularMoviesEntity> {

    @Query("DELETE FROM popularMovies")
    suspend fun clear()

    @Query("SELECT * FROM popularMovies ORDER BY popularity DESC")
    fun observePopular(): Flow<List<PopularMoviesEntity>>

    @Query("SELECT * FROM popularMovies ORDER BY voteAverage DESC")
    fun observeTopRated(): Flow<List<PopularMoviesEntity>>

    @Query("SELECT * FROM popularMovies ORDER BY popularity DESC")
    fun pagingSource(): PagingSource<Int, PopularMoviesEntity>
}
