package com.example.mvvmmoviesapp.data.repository

import com.example.mvvmmoviesapp.data.model.Movie
import com.example.mvvmmoviesapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}