package com.example.mvvmmoviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmmoviesapp.data.model.entitty.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}