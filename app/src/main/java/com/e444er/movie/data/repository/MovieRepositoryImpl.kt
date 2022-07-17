package com.e444er.movie.data.repository

import com.e444er.movie.common.Resource
import com.e444er.movie.common.SafeApiCall
import com.e444er.movie.data.remote.api.MovieApi
import com.e444er.movie.data.remote.dto.MovieListDTO
import com.e444er.movie.data.remote.dto.toMovieList
import com.e444er.movie.domain.model.MovieList
import com.e444er.movie.domain.repository.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val safeApiCall: SafeApiCall
) : MovieRepository {

    override suspend fun getMovieList(): MovieListDTO =
        api.getMovieList()


}