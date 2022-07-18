package com.e444er.movie.presentation.fragments.search

import com.e444er.movie.domain.model.Movie

data class SearchState(
    val isLoading: Boolean = false,
    val data: List<Movie>? = null,
    val error: String = ""
)