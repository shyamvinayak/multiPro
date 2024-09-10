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
import coil.annotation.ExperimentalCoilApi
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.BlackVerticalGradient
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.Cast
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MoviePosterDetail
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieTextContent
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.Overview
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.Poster
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.MovieDetails
import com.example.add_mul_by_kotlin_methods.ui.theme.backgroundNight

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieDetailsScreen(movieId: String, viewModel: MovieViewModel = hiltViewModel()) {

    println("MovieId:---$movieId")

    val movieDetails by viewModel.movieDetails.collectAsState()
    val state = rememberScrollState()
    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId.toInt())
    }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    /*Box(modifier = Modifier.fillMaxSize()) {
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        )
        {

              items(movieDetails.count()){
                  movieDetails[it].let {movie->
                      Poster(
                          imageUrl = movie.poster_path,
                          screenHeight = screenHeight,
                          runtime = 24,
                          releaseDate = "${viewModel.extractYear(movie.release_date)}",
                          voteAverage = movie.vote_average
                      )
                      HorizontalDivider(
                          modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                          thickness = 1.dp
                      )
                      Overview(overview = movie.overview)
                      HorizontalDivider(
                          modifier = Modifier
                              .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                          thickness = 1.dp
                      )
                      Cast(images = movie.casts)

                  }

              }

        }



    }*/
    movieDetails.forEach {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state)
                .background(MaterialTheme.colorScheme.surface) //backgroundNight
        ) {

            val (poster, textContent) = createRefs()
            MoviePosterDetail(path = it.poster_path,
                modifier = Modifier.constrainAs(poster) {
                    top.linkTo(parent.top)
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                })

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

