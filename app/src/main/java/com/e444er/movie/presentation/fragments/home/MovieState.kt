package com.e444er.movie.presentation.fragments.home

import com.e444er.movie.domain.model.Movie

data class MovieState(
    val isLoading: Boolean = false,
    val data: List<Movie>? = null,
    val error: String = ""
)