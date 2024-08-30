package com.example.add_mul_by_kotlin_methods.navigation

sealed class Screens(val route: String) {
    object SplashScreen: Screens("SplashScreen")
    object Home: Screens("home_screen")
    object Calculator: Screens("Calculator_screen")
    object BasicCal: Screens("BasicMath_screen")
    object SingleMovies: Screens("single_movie/{movieId}")
    object MoviesMain: Screens("movies_main")
    object WishList:Screens("wishListScreen")
    object Animation:Screens("AnimationScreen")
    object Calender:Screens("CalenderScreen")
    object PDFGen:Screens("PDFGeneratorScreen")
    object PhotoPic:Screens("PhotoPicScreen")
    object DBImage:Screens("DBImage")
}
