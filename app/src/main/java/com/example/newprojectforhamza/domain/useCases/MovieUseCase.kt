package com.example.newprojectforhamza.domain.useCases

import android.content.Context
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.data.repository.MovieRepository
import com.example.newprojectforhamza.domain.domainModels.Movie
import com.example.newprojectforhamza.domain.domainModels.PopularMoviesModel
import com.example.newprojectforhamza.domain.domainModels.TopRatedMoviesModel
import com.example.newprojectforhamza.domain.mapper.toDomain
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MovieUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: MovieRepository
) {

    /** 🆕 Emits one ResourceApiState<List<Movie>> that merges both sources. */
    suspend fun getAllMovies(): Flow<ResourceApiState<List<Movie>>> =
        combine(
            repository.getPopularMovies(),    /** Flow<ResourceApiState<List<PopularMoviesModel>>>*/
            repository.getTopRatedMovies()    /** Flow<ResourceApiState<List<TopRatedMoviesModel>>> */
        ) { pop, top ->

    /**  ---------- convenience mappers ---------- */
            fun List<PopularMoviesModel>?.toMovies() =
                this?.map { it.toDomain() } ?: emptyList()

            fun List<TopRatedMoviesModel>?.toMovies() =
                this?.map { it.toDomain() } ?: emptyList()

            /** ---------- merge states ----------*/
            when {
                // both succeeded
                pop is ResourceApiState.Success && top is ResourceApiState.Success -> {
                    val combined = (pop.data.toMovies() + top.data.toMovies())
                        .distinctBy { it.id }            /** remove duplicates by id */
                    ResourceApiState.Success(combined)
                }

           /**  popular failed, top succeeded (show what we have) */
                pop is ResourceApiState.Error && top is ResourceApiState.Success -> {
                    ResourceApiState.Error(
                        message   = pop.message ?: context.getString(R.string.popular_list_failed),
                        data      = top.data.toMovies(),   /* still show top‑rated */
                        throwable = pop.throwable
                    )
                }

     /**  top‑rated failed, popular succeeded */
                top is ResourceApiState.Error && pop is ResourceApiState.Success -> {
                    ResourceApiState.Error(
                        message   = top.message ?: context.getString(R.string.top_rated_list_failed),
                        data      = pop.data.toMovies(),
                        throwable = top.throwable
                    )
                }

        /**  both error (may still carry cached data) */
                pop is ResourceApiState.Error && top is ResourceApiState.Error -> {
                    ResourceApiState.Error(
                        message   = pop.message ?: top.message ?: context.getString(R.string.both_requests_failed),
                        data      = pop.data.toMovies() + top.data.toMovies(),
                        throwable = pop.throwable ?: top.throwable
                    )
                }

                else -> ResourceApiState.Loading()   
            }
        }
            .flowOn(Dispatchers.IO)
}
