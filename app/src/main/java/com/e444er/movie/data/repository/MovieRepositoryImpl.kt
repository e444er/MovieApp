package com.e444er.movie.data.repository

import com.e444er.movie.data.remote.api.MovieApi
import com.e444er.movie.data.remote.dto.MovieListDTO
import com.e444er.movie.domain.repository.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
) : MovieRepository {

    override suspend fun getTopWeek(): MovieListDTO {
        return api.getWeek()
    }


}