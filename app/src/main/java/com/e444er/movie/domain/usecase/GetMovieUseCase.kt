package com.e444er.movie.domain.usecase

import com.e444er.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repo: MovieRepository
) {

    suspend operator fun invoke(): Flow<List<Any>> = flow  {
       emit(repo.getMovies())
    }
}