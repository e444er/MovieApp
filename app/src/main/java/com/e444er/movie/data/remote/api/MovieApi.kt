package com.e444er.movie.data.remote.api

import com.e444er.movie.data.remote.dto.MovieListDTO
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/popular?api_key=271236134afbbdcd24c3caaaab027824&language=ru-RU&page=1")
    suspend fun getMovieList(): MovieListDTO

}