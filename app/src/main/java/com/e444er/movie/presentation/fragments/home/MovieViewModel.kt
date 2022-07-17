package com.e444er.movie.presentation.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e444er.movie.common.Resource
import com.e444er.movie.domain.usecase.GetListUseCase
import com.e444er.movie.domain.usecase.GetTopRatingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase,
    private val getTopRatingUseCase: GetTopRatingUseCase

) : ViewModel() {

    private val _listMovies = MutableStateFlow(MovieState())
    val trendingMovies: StateFlow<MovieState> = _listMovies.asStateFlow()

    private val _topMovies = MutableStateFlow(MovieState())
    val topMovies: StateFlow<MovieState> = _topMovies.asStateFlow()

    init {
        getList()
        getTopRating()
    }

    private fun getList() {
        getListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _listMovies.value = MovieState(data = result.data)
                }
                is Resource.Error -> {
                    _listMovies.value = MovieState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _listMovies.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTopRating() {
        getTopRatingUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _topMovies.value = MovieState(data = result.data)
                }
                is Resource.Error -> {
                    _topMovies.value = MovieState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _topMovies.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}
