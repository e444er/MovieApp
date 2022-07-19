package com.e444er.movie.domain.usecase

import com.e444er.movie.domain.model.Movie
import com.e444er.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieIdUseCase @Inject constructor(
    private val repo: MovieRepository
) {

    suspend operator fun invoke(id: Int): Movie? {
        return repo.getMoviesById(id)
    }
}