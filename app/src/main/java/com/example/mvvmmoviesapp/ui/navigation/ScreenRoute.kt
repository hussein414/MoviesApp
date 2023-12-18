package com.example.mvvmmoviesapp.ui.navigation

sealed class ScreenRoute(val rout: String) {
    object Home : ScreenRoute("main")
    object PopularMovieList : ScreenRoute("popularMovie")
    object UpcomingMovieList : ScreenRoute("upcomingMovie")
    object Details : ScreenRoute("details")
}
