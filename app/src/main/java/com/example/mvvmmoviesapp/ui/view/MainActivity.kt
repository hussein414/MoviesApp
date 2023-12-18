package com.example.mvvmmoviesapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvvmmoviesapp.ui.navigation.ScreenRoute
import com.example.mvvmmoviesapp.ui.theme.MVVMMoviesAppTheme
import com.example.mvvmmoviesapp.ui.view.component.SetBarColor
import com.example.mvvmmoviesapp.ui.view.screen.DetailsScreen
import com.example.mvvmmoviesapp.ui.view.screen.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMMoviesAppTheme {
                SetBarColor(color = MaterialTheme.colorScheme.inverseOnSurface)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoute.Home.rout
                    ) {
                        composable(ScreenRoute.Home.rout) {
                            HomeScreen(navController)
                        }
                        composable(
                            route =  ScreenRoute.Details.rout + "/{movieId}",
                            arguments = listOf(
                                navArgument("movieId") {
                                    type = NavType.IntType
                                }
                            )) {
                            DetailsScreen()
                        }
                    }
                }
            }
        }
    }
}
