package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.Entity.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.paging.map
import com.example.add_mul_by_kotlin_methods.Navigation.Screens
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.Movie
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.MovieData
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.MovieDetails
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.Entity.WishlistEntity
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.MovieDao
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.MovieRepository
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.mappers.toMovie
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.remote.MovieApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@HiltViewModel
class MovieViewModel @Inject constructor(
    pager: Pager<Int, MovieEntity>,
    private val movieApi: MovieApi,
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

    val moviePagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toMovie() }
        }.cachedIn(viewModelScope)

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val details = repository.getMovieDetails(movieId)
            _movieDetails.value = details
        }

    }

    fun addToWishList(movieId: Int,isFav:Boolean, isClicked: Boolean) {
        viewModelScope.launch {
            if(isClicked){
                repository.addToWishList(WishlistEntity(movieId = movieId, isFavorite = isFav))
                _wishlistStatus.value = _wishlistStatus.value.toMutableMap().apply { put(movieId, isFav) }

            }
            loadWishlistDetails()
        }
    }

    fun loadWishlistDetails() {
        viewModelScope.launch {
            _wishlistDetails.value = repository.getWishlistDetails( )
            _wishlistStatus.value = _wishlistDetails.value.associateBy({it.movie.movie_id},{true})
        }
    }

    fun removeWishList(movieId: Int) {
        viewModelScope.launch {
            repository.removeFromWishList(movieId)
            _wishlistStatus.value = _wishlistStatus.value.toMutableMap().apply {remove(movieId)}
            loadWishlistDetails()
        }
    }

    fun loadSuggestedMovie(){
        viewModelScope.launch {
            _suggestedMovieDetails.value = repository.getSuggestedMovies()
        }
    }

    fun extractYear(dateString: String): Int {
        val formatter = SimpleDateFormat("E, MM/dd/yyyy", Locale.US)
        val date = formatter.parse(dateString)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.YEAR)
    }


}