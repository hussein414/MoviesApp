package com.example.mvvmmoviesapp.ui.event

sealed interface MovieListUiEvent{
    data class Paginate(val category: String) : MovieListUiEvent
    object Navigate : MovieListUiEvent
}