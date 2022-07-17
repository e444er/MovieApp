package com.e444er.movie.domain.usecase

import com.e444er.movie.common.Resource
import com.e444er.movie.data.remote.dto.toMovie
import com.e444er.movie.domain.model.Movie
import com.e444er.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieRepository.getMovieList()
            val domainData =
                if (!data.results.isNullOrEmpty()) data.results
                    .map { it -> it.toMovie() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }
}