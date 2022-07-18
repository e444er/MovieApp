package com.e444er.movie.presentation.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.e444er.movie.common.Resource
import com.e444er.movie.data.paging.MovieListPagingSource
import com.e444er.movie.data.paging.TopRatingPagingSource
import com.e444er.movie.data.remote.api.MovieApi
import com.e444er.movie.domain.usecase.GetTopWeekUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val api: MovieApi,
    private val getTopWeekUseCase: GetTopWeekUseCase
) : ViewModel() {

    private val _topWeek = MutableStateFlow(MovieState())
    val topWeek: StateFlow<MovieState> = _topWeek.asStateFlow()

    val listPaging = Pager(PagingConfig(pageSize = 1)) {
        MovieListPagingSource(api)
    }.flow
        .cachedIn(viewModelScope)

    val topRatingPaging = Pager(PagingConfig(pageSize = 1)) {
        TopRatingPagingSource(api)
    }.flow
        .cachedIn(viewModelScope)

    init {
        getWeek()
    }

    private fun getWeek() {
        getTopWeekUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _topWeek.value = MovieState(data = result.data)
                }
                is Resource.Error -> {
                    _topWeek.value = MovieState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _topWeek.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
