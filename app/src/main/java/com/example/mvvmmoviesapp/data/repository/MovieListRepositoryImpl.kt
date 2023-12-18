package com.example.mvvmmoviesapp.data.repository

import com.example.mvvmmoviesapp.data.local.MovieDatabase
import com.example.mvvmmoviesapp.data.mapper.toMovie
import com.example.mvvmmoviesapp.data.mapper.toMovieEntity
import com.example.mvvmmoviesapp.data.model.Movie
import com.example.mvvmmoviesapp.data.remote.MovieApiService
import com.example.mvvmmoviesapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val movieDatabase: MovieDatabase
) : MovieListRepository {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading(true))
            val localMovieList = movieDatabase.movieDao.getMovieListByCategory(category)
            val shouldLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote
            if (shouldLocalMovie) {
                emit(
                    Resource.Success(data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie(category)
                    })
                )
                emit(Resource.Loading(true))
                return@flow
            }
            val movieListFromApi = try {
                movieApiService.getMoviesList(category, page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error Loading Movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error Loading Movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error Loading Movies"))
                return@flow
            }
            val movieEntity = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }
            movieDatabase.movieDao.upsertMovieList(movieEntity)
            emit(Resource.Success(movieEntity.map {
                it.toMovie(category)
            }))
            emit(Resource.Loading(false))
        }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading(true))
        val movieEntity = movieDatabase.movieDao.getMovieById(id)
        if (movieEntity != null) {
            emit(Resource.Success(movieEntity.toMovie(movieEntity.category)))
            emit(Resource.Loading(false))
            return@flow
        }
        emit(Resource.Error(message = "Error No Such Movies"))
        emit(Resource.Loading(false))
    }
}