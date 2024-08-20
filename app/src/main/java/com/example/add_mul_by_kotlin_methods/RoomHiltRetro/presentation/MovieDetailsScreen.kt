package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component.MovieDetailsCard
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component.MoviePoster

@Composable
fun MovieDetailsScreen(movieId: Int, viewModel: MovieViewModel = hiltViewModel()) {

    val movieDetails by viewModel.movieDetails.collectAsState()
    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }
    Toast.makeText(LocalContext.current,"Movie Id is:--$movieId",Toast.LENGTH_LONG).show()



}