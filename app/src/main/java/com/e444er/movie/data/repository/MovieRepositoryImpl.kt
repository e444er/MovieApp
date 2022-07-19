package com.e444er.movie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.e444er.movie.data.local.MovieDao
import com.e444er.movie.data.local.MovieEntity
import com.e444er.movie.data.mapper.toEntityMovie
import com.e444er.movie.data.mapper.toMovieEntity
import com.e444er.movie.data.remote.api.MovieApi
import com.e444er.movie.data.remote.dto.MovieListDTO
import com.e444er.movie.domain.model.Movie
import com.e444er.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val dao: MovieDao
) : MovieRepository {

    override suspend fun getTopWeek(): MovieListDTO {
        return api.getWeek()
    }

    override suspend fun getSearch(name: String): MovieListDTO {
        return api.searchMovie(name)
    }

    override suspend fun getMovies(): List<Movie> {
        return dao.getMovie().map { it.toEntityMovie() }
    }

    override suspend fun getMoviesById(id: Int): Movie? {
        return dao.getMovieById(id)?.toEntityMovie()
    }

    override suspend fun insertMovies(movie: Movie) {
        return dao.insertMovie(movie.toMovieEntity())
    }

    override suspend fun deleteMovies(movie: Movie) {
        return dao.deleteMovie(movie.toMovieEntity())
    }
}