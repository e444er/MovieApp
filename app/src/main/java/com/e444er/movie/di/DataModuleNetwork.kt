package com.e444er.movie.di

import com.e444er.movie.data.remote.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModuleNetwork {

    private const val BASE_URL = "https://api.themoviedb.org/3/"


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)
//
//    @Singleton
//    @Provides
//    fun provideTvApi(retrofit: Retrofit): TvApi = retrofit.create(TvApi::class.java)
//
//    @Singleton
//    @Provides
//    fun providePersonApi(retrofit: Retrofit): PersonApi = retrofit.create(PersonApi::class.java)
}