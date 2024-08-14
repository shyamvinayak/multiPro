package com.example.add_mul_by_kotlin_methods.Navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.add_mul_by_kotlin_methods.SplashScreen
import com.example.add_mul_by_kotlin_methods.BasicMath.ShowListContent
import com.example.add_mul_by_kotlin_methods.Calculator.ui.Calculator
import com.example.add_mul_by_kotlin_methods.Home.HomeScreen
import com.example.add_mul_by_kotlin_methods.RetrofitPro.ui.MovieMainList
import com.example.add_mul_by_kotlin_methods.RetrofitPro.ui.SingleMovie

@Composable
fun NavigationGraph ( ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) { SplashScreen(navController) }
        composable(Screens.Home.route) { HomeScreen("Projects", navController) }
        composable(Screens.Calculator.route) { Calculator() }
        composable(Screens.MoviesMain.route) { MovieMainList(navController) }
        composable(
            route = Screens.SingleMovies.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            SingleMovie(movieId = "$movieId")
        }
        composable(Screens.BasicCal.route) {
            ShowListContent(
                "Calculate Addition & Multiplication of two numbers",
                Modifier.padding(all = 20.dp)
            )
        }
    }
}