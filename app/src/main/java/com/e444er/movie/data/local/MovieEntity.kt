package com.e444er.movie.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_db")
data class MovieEntity(
    val character: String?,
    @PrimaryKey
    val id: Int,
    val job: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double
)