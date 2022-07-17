package com.e444er.movie.domain.usecase

import com.e444er.movie.common.Resource
import com.e444er.movie.data.mapper.toMovie
import com.e444er.movie.domain.model.Movie
import com.e444er.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopWeekUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val data = movieRepository.getTopWeek()
            val domainData =
                if (data.results.isNotEmpty()) data.results
                    .map { it.toMovie() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }
}
