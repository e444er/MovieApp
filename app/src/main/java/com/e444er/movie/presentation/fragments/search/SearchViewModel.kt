package com.e444er.movie.presentation.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e444er.movie.common.Resource
import com.e444er.movie.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {

    private val _searchMovie = MutableStateFlow(SearchState())
    val searchMovie: StateFlow<SearchState> = _searchMovie.asStateFlow()

    suspend fun searchName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSearchUseCase(name).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _searchMovie.value = SearchState(data = result.data)
                    }
                    is Resource.Error -> {
                        _searchMovie.value = SearchState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _searchMovie.value = SearchState(isLoading = true)
                    }
                }
            }
        }
    }
}
