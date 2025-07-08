package com.example.newprojectforhamza.data.local.utils

import com.example.newprojectforhamza.data.local.dao.PopularMovieDao
import com.example.newprojectforhamza.data.local.dao.TopRatedMovieDao
import com.example.newprojectforhamza.data.remote.models.PopularResultDTO
import com.example.newprojectforhamza.data.remote.models.TopRatedResultDTO

suspend fun <T> PopularMovieDao.replaceAll(list: List<T>) where T : PopularResultDTO {
    clear()
    insertAll(list)
}

suspend fun <T> TopRatedMovieDao.replaceAll(list: List<T>) where T : TopRatedResultDTO {
    clear()
    insertAll(list)
}
