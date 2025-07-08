package com.example.newprojectforhamza.presentation.ui.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.domain.domainModels.Movie
import com.example.newprojectforhamza.domain.domainModels.TopRatedMoviesModel
import com.example.newprojectforhamza.domain.useCases.MovieUseCase
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TopViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCase: MovieUseCase
) : ViewModel() {

    private val _moviesState = MutableStateFlow<ResourceApiState<List<TopRatedMoviesModel>>>(ResourceApiState.Loading())
    val moviesState: StateFlow<ResourceApiState<List<TopRatedMoviesModel>>> = _moviesState.asStateFlow()

    init { fetchMovies() }

    fun fetchMovies() {
        viewModelScope.launch {
            useCase.getTopRatedMovies()
                .catch { e ->
                    _moviesState.value = ResourceApiState.Error(
                        message   = e.localizedMessage ?: context.getString(R.string.unknown_error),
                        throwable = e
                    )
                }
                .collect { state ->
                    _moviesState.value = state
                }
        }
    }
}

