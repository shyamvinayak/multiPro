package com.example.add_mul_by_kotlin_methods.Navigation

sealed class Screens(val route: String) {
    object SplashScreen: Screens("SplashScreen")
    object Home: Screens("home_screen")
    object Calculator: Screens("Calculator_screen")
    object BasicCal: Screens("BasicMath_screen")
    object Retrofit: Screens("Retrofit_screen")
    object SingleMovies: Screens("single_movie/{movieId}")
    object MoviesMain: Screens("movies_main")
    object WishList:Screens("wishListScreen")
}
