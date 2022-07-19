package com.e444er.movie.domain.repository

import com.e444er.movie.data.remote.dto.MovieListDTO
import com.e444er.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getTopWeek(): MovieListDTO

    suspend fun getSearch(name: String): MovieListDTO

    suspend fun getMovies(): List<Movie>

    suspend fun getMoviesById(id: Int): Movie?

    suspend fun insertMovies(movie: Movie)

    suspend fun deleteMovies(movie: Movie)
}