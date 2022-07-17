package com.e444er.movie.data.remote.api

import com.e444er.movie.data.remote.dto.MovieListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?api_key=271236134afbbdcd24c3caaaab027824&language=ru-RU")
    suspend fun getMovieList(
        @Query ("page") page: Int
    ): MovieListDTO

    @GET("movie/top_rated?api_key=271236134afbbdcd24c3caaaab027824&language=ru-Ru")
    suspend fun getTopRating(
        @Query ("page") page: Int
    ): MovieListDTO


    @GET("trending/movie/week?api_key=271236134afbbdcd24c3caaaab027824&language=ru-Ru")
    suspend fun getWeek(): MovieListDTO

}