package com.example.mvvmmoviesapp.data.mapper

import com.example.mvvmmoviesapp.data.model.Movie
import com.example.mvvmmoviesapp.data.model.dto.MovieDto
import com.example.mvvmmoviesapp.data.model.entitty.MovieEntity

fun MovieDto.toMovieEntity(
    category: String
): MovieEntity = MovieEntity(
    adult = adult ?: false,
    backdrop_path = backdrop_path ?: "",
    original_language = original_language ?: "",
    overview = overview ?: "",
    poster_path = poster_path ?: "",
    release_date = release_date ?: "",
    title = title ?: "",
    vote_average = vote_average ?: 0.0,
    popularity = popularity ?: 0.0,
    vote_count = vote_count ?: 0,
    id = id ?: -1,
    original_title = original_title ?: "",
    video = video ?: false,

    category = category,

    genre_ids = try {
        genre_ids?.joinToString(",") ?: "-1,-2"
    } catch (e: Exception) {
        "-1,-2"
    }
)

fun MovieEntity.toMovie(
    category: String
): Movie = Movie(
    backdrop_path = backdrop_path,
    original_language = original_language,
    overview = overview,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    vote_average = vote_average,
    popularity = popularity,
    vote_count = vote_count,
    video = video,
    id = id,
    adult = adult,
    original_title = original_title,

    category = category,

    genre_ids = try {
        genre_ids.split(",").map { it.toInt() }
    } catch (e: Exception) {
        listOf(-1, -2)
    }
)