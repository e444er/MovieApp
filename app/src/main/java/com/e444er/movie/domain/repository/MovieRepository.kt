package com.e444er.movie.domain.repository

import com.e444er.movie.data.remote.dto.MovieListDTO

interface MovieRepository {

    suspend fun getMovieList(): MovieListDTO

    suspend fun getTopRating(): MovieListDTO

    suspend fun getTopWeek(): MovieListDTO

}