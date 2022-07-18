package com.e444er.movie.domain.repository

import com.e444er.movie.data.remote.dto.MovieListDTO

interface MovieRepository {

    suspend fun getTopWeek(): MovieListDTO

    suspend fun getSearch(name: String): MovieListDTO
}