package com.e444er.movie.domain.usecase

import com.e444er.movie.domain.model.Movie
import com.e444er.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieAddUseCase @Inject constructor(
    private val repo: MovieRepository
) {

    suspend operator fun invoke(movie: Movie) {
        repo.insertMovies(movie)
    }
}