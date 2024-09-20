package com.example.add_mul_by_kotlin_methods.navigation

import TestCoroutine
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.add_mul_by_kotlin_methods.animation.AnimationScreen
import com.example.add_mul_by_kotlin_methods.SplashScreen
import com.example.add_mul_by_kotlin_methods.basicMath.ShowListContent
import com.example.add_mul_by_kotlin_methods.calculator.ui.Calculator
import com.example.add_mul_by_kotlin_methods.calender.CalendarApp
import com.example.add_mul_by_kotlin_methods.dbImage.presentation.DBImage
import com.example.add_mul_by_kotlin_methods.home.HomeScreen
import com.example.add_mul_by_kotlin_methods.pdfGen.PDFGenerator
import com.example.add_mul_by_kotlin_methods.photoPic.Presentation.ImageEditor
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation.MovieDetailsScreen
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation.MovieScreen
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation.WishListScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
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
        ) {
            HomeScreen(navController)
        }

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

        composable(Screens.DBImage.route,
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
            }) {  DBImage() }

        composable(Screens.Coroutines.route,
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
            }) {  TestCoroutine(navController) }


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
            MovieDetailsScreen(navController,"$movieId")
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