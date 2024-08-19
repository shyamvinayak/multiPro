package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component.MovieDetailsCard

@Composable
fun MovieDetailsScreen(movieId: Int, viewModel: MovieViewModel = hiltViewModel()) {

    val movieDetails by viewModel.movieDetails.collectAsState()
    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }

    movieDetails?.let {nonNullMovieDetails->
        LazyColumn {
            items(nonNullMovieDetails){movie->
                MovieDetailsCard(
                    title = movie.original_title,
                    voteRange = movie.vote_average,
                    thumbnailUrl = movie.poster_path,
                    overview = movie.overview
                )

            }
        }

    }


}