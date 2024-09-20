package com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.CloseMovie
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MoviePosterDetail
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieTextContent

@Composable
fun MovieDetailsScreen(navController: NavController,movieId: String, viewModel: MovieViewModel = hiltViewModel()) {

    println("MovieId:---$movieId")

    val movieDetails by viewModel.movieDetails.collectAsState()
    val state = rememberScrollState()
    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId.toInt())
    }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    movieDetails.forEach {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state)
                .background(MaterialTheme.colorScheme.surface) //backgroundNight
        ) {

            val (poster, close,textContent) = createRefs()
            MoviePosterDetail(path = it.poster_path,
                modifier = Modifier.constrainAs(poster) {
                    top.linkTo(parent.top)
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                })

            CloseMovie(
                navController = navController,
                modifier = Modifier.constrainAs(close) {
                    top.linkTo(parent.top, 24.dp)
                    start.linkTo(parent.start, 24.dp)
                }
            )

            MovieTextContent(
                movie = it,
                modifier = Modifier
                    .constrainAs(textContent) {
                        linkTo(
                            top = poster.bottom,
                            topMargin = -150.dp,
                            bottom = parent.bottom,
                            bias = 0f
                        )
                        linkTo(
                            start = parent.start,
                            end = parent.end
                        )
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            )



        }
    }

}

