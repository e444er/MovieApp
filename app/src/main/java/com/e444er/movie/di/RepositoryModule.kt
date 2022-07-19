package com.e444er.movie.di

import com.e444er.movie.data.repository.MovieRepositoryImpl
import com.e444er.movie.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
//
//    @Binds
//    abstract fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

}