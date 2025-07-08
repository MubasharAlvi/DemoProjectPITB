package com.example.newprojectforhamza.data.repository

import android.content.Context
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.data.local.dao.PopularMovieDao
import com.example.newprojectforhamza.data.local.dao.TopRatedMovieDao
import com.example.newprojectforhamza.data.local.utils.replaceAll
import com.example.newprojectforhamza.data.mapper.toPopularDomain
import com.example.newprojectforhamza.data.mapper.toTopRatedDomain
import com.example.newprojectforhamza.data.remote.apiService.ApiService
import com.example.newprojectforhamza.data.remote.secretKey.SecretProvider
import com.example.newprojectforhamza.domain.domainModels.PopularMoviesModel
import com.example.newprojectforhamza.domain.domainModels.TopRatedMoviesModel
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import com.example.newprojectforhamza.presentation.utils.isOnline
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: ApiService,
    private val daoPopular: PopularMovieDao,
    private val daoTop: TopRatedMovieDao,
    private val secretProvider: SecretProvider
) : MovieRepository {

    /** override suspend fun getPopularMovies(): Flow<ResourceApiState<List<PopularMoviesModel>>> =
        networkBoundResource(
            isOnline = { context.isOnline() },
            queryLocal = { daoPopular.observePopular().map { list -> list.map { it.toPopularDomain() } } },
            fetchRemote = { api.popularMovies(context.getString(R.string.popular), apiKey = secretProvider.apiKey).resultsMovie },
            saveRemote = {
                daoPopular.clear()
                daoPopular.insertAll(it.map { dto -> dto.toEntity() })
            }
        )

    override suspend fun getTopRatedMovies(): Flow<ResourceApiState<List<TopRatedMoviesModel>>> =
        networkBoundResource(
            isOnline = { context.isOnline() },
            queryLocal = { daoTop.observeTopRated().map { it.map { it.toTopRatedDomain() } } },
            fetchRemote = { api.topRatedMovies(context.getString(R.string.top_rated), apiKey = secretProvider.apiKey).topRatedResultDTOs },
            saveRemote = {
                daoTop.clear()
                daoTop.insertAll(it.map { dto -> dto.toEntity() })
            }
        )*/


    override suspend fun getPopularMovies(): Flow<ResourceApiState<List<PopularMoviesModel>>> =
        networkBoundResource(
            isOnline    = { context.isOnline() },
            queryLocal  = { daoPopular.observePopular().map { it.map { e -> e.toPopularDomain() } } },
            fetchRemote = {
                api.popularMovies(
                    context.getString(R.string.popular),
                    apiKey = secretProvider.apiKey
                ).results
            },
            saveRemote  = { remote -> daoPopular.replaceAll(remote) }
        )

    override suspend fun getTopRatedMovies(): Flow<ResourceApiState<List<TopRatedMoviesModel>>> =
        networkBoundResource(
            isOnline    = { context.isOnline() },
            queryLocal  = { daoTop.observeTopRated().map { it.map { e -> e.toTopRatedDomain() } } },
            fetchRemote = {
                api.topRatedMovies(
                    context.getString(R.string.top_rated),
                    apiKey = secretProvider.apiKey
                ).results
            },
            saveRemote  = { remote -> daoTop.replaceAll(remote) }
        )

    inline fun <RemoteT, DomainT> networkBoundResource(
        crossinline isOnline: suspend () -> Boolean,
        crossinline queryLocal: () -> Flow<List<DomainT>>,
        crossinline fetchRemote: suspend () -> List<RemoteT>,
        crossinline saveRemote: suspend (List<RemoteT>) -> Unit
    ): Flow<ResourceApiState<List<DomainT>>> = flow {
        emit(ResourceApiState.Loading())

        val cache = queryLocal().first()
        emit(ResourceApiState.Success(cache))

        if (isOnline()) {
            try {
                val remote = fetchRemote()
                saveRemote(remote)
                val fresh = queryLocal().first()
                emit(ResourceApiState.Success(fresh))
            } catch (e: Exception) {
                emit(ResourceApiState.Error(message = e.localizedMessage ?: "Unknown error", data = cache, throwable = e))
            }
        }
    }

}
