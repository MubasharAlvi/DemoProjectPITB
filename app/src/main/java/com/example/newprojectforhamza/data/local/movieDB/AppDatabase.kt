package com.example.newprojectforhamza.data.local.movieDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newprojectforhamza.data.local.dao.PopularMovieDao
import com.example.newprojectforhamza.data.local.dao.TopRatedMovieDao
import com.example.newprojectforhamza.data.remote.models.PopularResultDTO
import com.example.newprojectforhamza.data.remote.models.TopRatedResultDTO
import com.example.newprojectforhamza.data.utils.Converters

@Database(
    entities = [PopularResultDTO::class, TopRatedResultDTO::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun popularMovieDao(): PopularMovieDao
    abstract fun topRatedMovieDao(): TopRatedMovieDao
}
