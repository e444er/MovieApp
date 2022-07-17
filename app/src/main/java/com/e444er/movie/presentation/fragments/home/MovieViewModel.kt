package com.e444er.movie.presentation.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e444er.movie.common.Resource
import com.e444er.movie.domain.usecase.GetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase
) : ViewModel() {

    private val _listMovies = MutableStateFlow(MovieState())
    val trendingMovies: StateFlow<MovieState> = _listMovies.asStateFlow()

    private var pagePopular = 1

    init {
        getList()
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

}
