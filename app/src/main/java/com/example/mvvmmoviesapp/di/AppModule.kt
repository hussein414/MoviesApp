package com.example.mvvmmoviesapp.di

import android.app.Application
import androidx.room.Room
import com.example.mvvmmoviesapp.data.local.MovieDatabase
import com.example.mvvmmoviesapp.data.remote.MovieApiService
import com.example.mvvmmoviesapp.utils.Constance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun providesMovieApi(): MovieApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constance.BASE_URL)
        .client(client)
        .build()
        .create(MovieApiService::class.java)

    @Provides
    @Singleton
    fun providesMovieDatabase(application: Application):MovieDatabase=
        Room.databaseBuilder(
            application,
            MovieDatabase::class.java,
            "movie.db"
        ).build()

}