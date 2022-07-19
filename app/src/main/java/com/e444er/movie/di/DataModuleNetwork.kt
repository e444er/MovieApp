package com.e444er.movie.di

import android.app.Application
import androidx.room.Room
import com.e444er.movie.data.local.MovieDatabase
import com.e444er.movie.data.remote.api.MovieApi
import com.e444er.movie.data.repository.MovieRepositoryImpl
import com.e444er.movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "movie_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: MovieDatabase, api: MovieApi): MovieRepository {
        return MovieRepositoryImpl(api, db.noteDao)
    }
}