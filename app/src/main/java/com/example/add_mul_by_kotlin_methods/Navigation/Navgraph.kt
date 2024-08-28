package com.example.add_mul_by_kotlin_methods.Navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import java.util.Calendar
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.add_mul_by_kotlin_methods.Animation.Presentation.AnimationScreen
import com.example.add_mul_by_kotlin_methods.SplashScreen
import com.example.add_mul_by_kotlin_methods.BasicMath.ShowListContent
import com.example.add_mul_by_kotlin_methods.Calculator.ui.Calculator
import com.example.add_mul_by_kotlin_methods.Calender.AllYearCalender
import com.example.add_mul_by_kotlin_methods.Calender.CalendarApp
import com.example.add_mul_by_kotlin_methods.Calender.Calender
import com.example.add_mul_by_kotlin_methods.Home.HomeScreen
import com.example.add_mul_by_kotlin_methods.PDFGen.PDFGenerator
import com.example.add_mul_by_kotlin_methods.PhotoPic.ImageEditor
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation.MovieDetailsScreen
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation.MovieScreen
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation.WishListScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val calendar = Calendar.getInstance()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route,
            enterTransition = { return@composable fadeIn(tween(1000)) },
            exitTransition = { return@composable fadeOut(tween(700)) },
            popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }
        ) { SplashScreen(navController) }
        composable(
            Screens.Home.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }
        ) { HomeScreen("Projects", navController) }
        composable(Screens.Calculator.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            exitTransition = {
                return@composable shrinkOut (tween(700) )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }) { Calculator() }
        composable(Screens.MoviesMain.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            exitTransition = {
                return@composable shrinkOut (tween(700) )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }) { MovieScreen(navController) }
        composable(Screens.WishList.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            exitTransition = {
                return@composable shrinkOut (tween(700) )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }) { WishListScreen(navController) }
        composable(Screens.Animation.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            exitTransition = {
                return@composable shrinkOut (tween(700) )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }) { AnimationScreen() }
        composable(Screens.Calender.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            exitTransition = {
                return@composable shrinkOut (tween(700) )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }) {
            CalendarApp() }
        composable(Screens.PDFGen.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            exitTransition = {
                return@composable shrinkOut (tween(700) )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }) { PDFGenerator() }
        composable(Screens.PhotoPic.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            exitTransition = {
                return@composable shrinkOut (tween(700) )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }) {  ImageEditor() }
        composable(
            route = Screens.SingleMovies.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType }),
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            exitTransition = {
                return@composable shrinkOut (tween(700) )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            println("MovieIdFromApi:---$movieId")
            MovieDetailsScreen("$movieId")
        }
        composable(Screens.BasicCal.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            exitTransition = {
                return@composable shrinkOut (tween(700) )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }) {
            ShowListContent(
                "Calculate Addition & Multiplication of two numbers",
                Modifier.padding(all = 20.dp)
            )
        }
    }
}