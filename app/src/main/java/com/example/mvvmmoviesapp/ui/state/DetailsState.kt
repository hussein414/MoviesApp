package com.example.mvvmmoviesapp.ui.state

import com.example.mvvmmoviesapp.data.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)
