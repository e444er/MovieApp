package com.e444er.movie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e444er.movie.domain.model.Movie

@Database(
    entities = [MovieEntity::class], version = 1, exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract val noteDao: MovieDao
}