package com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.paging.map
import com.example.add_mul_by_kotlin_methods.dbImage.room.ImageEntity
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.MovieDetails
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.WishlistEntity
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.MovieRepository
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.mappers.toMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlinx.coroutines.delay
import java.util.Locale
import java.time.Duration
import java.time.Duration.ofMinutes
import java.time.Duration.ofSeconds

@HiltViewModel
class MovieViewModel @Inject constructor(
    pager: Pager<Int, MovieEntity>,
    private val repository: MovieRepository,
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<List<MovieEntity>>(emptyList())
    val movieDetails: StateFlow<List<MovieEntity>> get() = _movieDetails

    private val _wishlistStatus = mutableStateOf<Map<Int, Boolean>>(emptyMap())
    val wishlistStatus: State<Map<Int, Boolean>> get() = _wishlistStatus

    private val _wishlistDetails = mutableStateOf<List<MovieDetails>>(emptyList())
    val wishlistDetails: State<List<MovieDetails>> get() = _wishlistDetails

    private val _suggestedMovieDetails = mutableStateOf<List<MovieDetails>>(emptyList())
    val suggestedMovieDetails: State<List<MovieDetails>> get() = _suggestedMovieDetails

    private val _currentMovie = MutableStateFlow<List<MovieEntity>>(emptyList())
    val currentMovie: StateFlow<List<MovieEntity>> get() = _currentMovie

    private val _searchDetails = MutableStateFlow<List<MovieEntity>>(emptyList())
    val searchDetails: StateFlow<List<MovieEntity>> get() = _searchDetails


    init {
        viewModelScope.launch {
            _currentMovie.value = repository.getAllMoviesNoPagination()
        }
    }

    val moviePagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map {
                it.toMovie()
            }
        }.cachedIn(viewModelScope)


    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val details = repository.getMovieDetails(movieId)
            _movieDetails.value = details
        }

    }

    fun addToWishList(movieId: Int, isFav: Boolean, isClicked: Boolean) {
        viewModelScope.launch {
            if (isClicked) {
                repository.addToWishList(WishlistEntity(movieId = movieId, isFavorite = isFav))
                _wishlistStatus.value =
                    _wishlistStatus.value.toMutableMap().apply { put(movieId, isFav) }

            }
            loadWishlistDetails()
        }
    }

    fun loadWishlistDetails() {
        viewModelScope.launch {
            _wishlistDetails.value = repository.getWishlistDetails()
            _wishlistStatus.value =
                _wishlistDetails.value.associateBy({ it.movie.movie_id }, { true })
        }
    }

    fun removeWishList(movieId: Int) {
        viewModelScope.launch {
            repository.removeFromWishList(movieId)
            _wishlistStatus.value = _wishlistStatus.value.toMutableMap().apply { remove(movieId) }
            loadWishlistDetails()
        }
    }

    fun loadSuggestedMovie() {
        viewModelScope.launch {
            _suggestedMovieDetails.value = repository.getSuggestedMovies()
        }
    }

    fun extractYear(dateString: String): Int {
        val formatter = SimpleDateFormat("E, MM/dd/yyyy", Locale.US)
        val date = formatter.parse(dateString)
        val calendar = Calendar.getInstance()
        if (date != null) {
            calendar.time = date
        }
        return calendar.get(Calendar.YEAR)
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                _searchDetails.value = repository.searchMovies(query)
                println("Search Items length:---${searchDetails.value.size}")
            } catch (e: Exception) {
                println("Search Error:---$e")
            }


        }
    }

    fun clearSearchResults() {
        _searchDetails.value = emptyList()
    }

    var currentIndex = 0
        set(value) {
            field = value
            viewModelScope.launch {
                delay(10_000)
                _currentMovie.value = repository.getAllMoviesNoPagination()
                delay(60_000)
                _currentMovie.value = repository.getAllMoviesNoPagination()
                delay(300_000)
                _currentMovie.value = repository.getAllMoviesNoPagination()
            }
        }


}