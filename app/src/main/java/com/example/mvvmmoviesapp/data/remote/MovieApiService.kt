package com.example.mvvmmoviesapp.data.remote

import com.example.mvvmmoviesapp.data.model.dto.MovieListDto
import com.example.mvvmmoviesapp.utils.Constance
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET(Constance.END_POINT)
    suspend fun getMoviesList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Constance.API_KEY
    ): MovieListDto
}