package com.example.add_mul_by_kotlin_methods.RetrofitPro

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.add_mul_by_kotlin_methods.RetrofitPro.RoomDB.MovieDatabase
import com.example.add_mul_by_kotlin_methods.RetrofitPro.RoomDB.MovieEntity
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.CommonDataItem
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.Data
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.MovieHomeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val movieDatabase: MovieDatabase
) {
    suspend fun getSingleMovie(): List<CommonDataItem> {
        return apiService.getSingleMovie()
    }

    suspend fun getMovieList(): MovieHomeModel {
        return apiService.getMovieList( )
    }

    suspend fun getPaginatedMovieList(page:Int): MovieHomeModel {
        return apiService.getMovies(page)
    }


    fun getAllMoviesFromDatabase(): List<Data> {
        return movieDatabase.movieDao().getAllMovies()
    }

    suspend fun getMoviesById(movieId:Int): Data? {
        return movieDatabase.movieDao().getMovieById(movieId)
    }

    suspend fun insertMovieToDatabase(movie: MovieEntity) {
        movieDatabase.movieDao().insert(movie)
    }

    suspend fun clearMoviesFromDatabase() {
        movieDatabase.movieDao().deleteAll()
    }
}
