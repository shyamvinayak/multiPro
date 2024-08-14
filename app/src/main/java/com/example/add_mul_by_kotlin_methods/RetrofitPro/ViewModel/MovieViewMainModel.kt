package com.example.add_mul_by_kotlin_methods.RetrofitPro.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.Data
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.toMovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.add_mul_by_kotlin_methods.RetrofitPro.Paging.MoviePagingSource
import com.example.add_mul_by_kotlin_methods.RetrofitPro.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow

enum class STATE {
    LOADING,
    SUCCESS,
    FAILED
}

@HiltViewModel
class MovieViewMainModel @Inject constructor(
    private val repository: Repository,
    private val moviePagingSource:MoviePagingSource
) :
    ViewModel() {

    var state by mutableStateOf(STATE.LOADING)

    val page:Int = 1
    private var _movieList = MutableStateFlow<List<Data>>(emptyList())
    val movieList: StateFlow<List<Data>> get() = _movieList

    private val _movieResponse: MutableStateFlow<PagingData<Data>> =
        MutableStateFlow(PagingData.empty())
    var movieResponse = _movieResponse.asStateFlow()
        private set

    private var _singleMovie = MutableStateFlow<Data?>(null)
    val singleMovie: StateFlow<Data?> get() = _singleMovie



    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                moviePagingSource
            }.flow.cachedIn(viewModelScope).collect {
                _movieResponse.value = it
            }
        }
    }

   suspend fun getMovieByApi(){
       val moviesPaginated = repository.getPaginatedMovieList(page)
       if(moviesPaginated.data.isNotEmpty()){
           _movieList.value = moviesPaginated.data
           val movieEntities = _movieList.value.map { it.toMovieEntity() }
           movieEntities.forEach { movie ->
               repository.insertMovieToDatabase(movie)
           }
       }
    }

    suspend fun getMovieById(movieId: String) {
        println("SingleDataId:---$movieId")
        try {
            println("ToInt:---${movieId.toInt()}")
            val movie = repository.getMoviesById(movieId.toInt())
            _singleMovie.value = movie
            println("SingleData:---$movie")
        } catch (e: Exception) {
            println("Error fetching movie by ID: $e")
            state = STATE.FAILED
        }
    }



   /* private var _movieList = MutableStateFlow<List<Data>>(emptyList())
    val movieList: StateFlow<List<Data>> get() = _movieList

    private var _singleMovie = MutableStateFlow<Data?>(null)
    val singleMovie: StateFlow<Data?> get() = _singleMovie

    var state by mutableStateOf(STATE.LOADING)


    init {
       addMovieList()
    }

    fun getPaginatedList():Pager<Int,Data>{
        return  Pager(config = PagingConfig(10)){
            repository.getAllMoviesFromDatabase()
        }
    }


    fun addMovieList(){
        viewModelScope.launch (Dispatchers.IO){
           try {
               val response = repository.getPaginatedMovieList()
               if(response.data.isNotEmpty()){
                   _movieList.value = response.data
                   val movieEntities = _movieList.value.map { it.toMovieEntity() }
                   movieEntities.forEach { movie ->
                       repository.insertMovieToDatabase(movie)
                   }
               }
           }catch (e:Exception){
               println("Api Error:--$e")
           }
        }
    }


    suspend fun getMovieById(movieId: String) {
        println("SingleDataId:---$movieId")
        try {
            println("ToInt:---${movieId.toInt()}")
            val movie = repository.getMoviesById(movieId.toInt())
            _singleMovie.value = movie
            println("SingleData:---$movie")
        } catch (e: Exception) {
            println("Error fetching movie by ID: $e")
            state = STATE.FAILED
        }
    }*/

}


/*var state by mutableStateOf(STATE.LOADING)

    private val _movieResponse: MutableStateFlow<PagingData<Data>> =
        MutableStateFlow(PagingData.empty())
    var movieResponse = _movieResponse.asStateFlow()
        private set

   /* private var _singleMovie = MutableStateFlow<Data?>(null)
    val singleMovie: StateFlow<Data?> get() = _singleMovie*/

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                moviePagingSource
            }.flow.cachedIn(viewModelScope).collect {
                _movieResponse.value = it
            }
        }
    }

    suspend fun getMovieById(movieId: String) {
        println("SingleDataId:---$movieId")
        try {
            println("ToInt:---${movieId.toInt()}")
            val movie = repository.getMoviesById(movieId.toInt())
            _singleMovie.value = movie
            println("SingleData:---$movie")
        } catch (e: Exception) {
            println("Error fetching movie by ID: $e")
            state = STATE.FAILED
        }
    }*/

