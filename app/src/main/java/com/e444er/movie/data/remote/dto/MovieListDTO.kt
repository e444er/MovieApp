package com.e444er.movie.data.remote.dto


import com.e444er.movie.domain.model.Movie
import com.e444er.movie.domain.model.MovieList
import com.google.gson.annotations.SerializedName

data class MovieListDTO(
    @SerializedName("results")
    val results: List<MovieDTO>,
    @SerializedName("total_results")
    val totalResults: Int
)

internal fun MovieListDTO.toMovieList() = MovieList(results.map { it.toMovie() }, totalResults)

data class MovieDTO(
    @SerializedName("character")
    val character: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("job")
    val job: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)

fun MovieDTO.toMovie(): Movie {
    return Movie(
       character, id, job, overview, posterPath, releaseDate, title, voteAverage
    )
}