package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.paging.map
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.MovieDao
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.mappers.toMovie
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.remote.MovieDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

@HiltViewModel
class MovieViewModel @Inject constructor(
    pager:Pager<Int,MovieEntity>,
    private val movieDao: MovieDao,
) :ViewModel(){

    private val _movieDetails = MutableStateFlow<List<MovieEntity>?>(null)
    val movieDetails: StateFlow<List<MovieEntity>?> get() = _movieDetails

    val moviePagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toMovie() }
        }.cachedIn(viewModelScope)

    suspend fun getMovieDetails(movieId: Int) {
        _movieDetails.value = movieDao.loadSingleMovie(movieId)
    }

}