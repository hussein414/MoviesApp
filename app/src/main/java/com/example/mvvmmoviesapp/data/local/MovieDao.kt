package com.example.mvvmmoviesapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mvvmmoviesapp.data.model.entitty.MovieEntity

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovieList(movieEntity: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE id=:id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM MovieEntity WHERE category=:category")
    suspend fun getMovieListByCategory(category: String): List<MovieEntity>
}