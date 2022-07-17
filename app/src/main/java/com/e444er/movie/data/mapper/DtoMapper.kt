package com.e444er.movie.data.mapper

import com.e444er.movie.data.remote.dto.MovieDTO
import com.e444er.movie.data.remote.dto.MovieListDTO
import com.e444er.movie.domain.model.Movie
import com.e444er.movie.domain.model.MovieList

internal fun MovieListDTO.toMovieList() = MovieList(results.map { it.toMovie() }, totalResults)

fun MovieDTO.toMovie(): Movie {
    return Movie(
        character, id, job, overview, posterPath, releaseDate, title, voteAverage
    )
}