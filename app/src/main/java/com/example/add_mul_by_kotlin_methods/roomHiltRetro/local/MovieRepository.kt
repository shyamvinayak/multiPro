package com.example.add_mul_by_kotlin_methods.roomHiltRetro.local

import androidx.room.Query
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.MovieDetails
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.MovieEntity
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.WishlistEntity
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend fun addToWishList(wishlistEntity: WishlistEntity): Boolean {
        val rowId = movieDao.addToWishList(wishlistEntity)
        return rowId > 0
    }
    suspend fun getMovieDetails(movieId: Int): List<MovieEntity> {
        return movieDao.loadSingleMovie(movieId)
    }

    suspend fun getAllMoviesNoPagination():List<MovieEntity>{
        return  movieDao.getAllMovies()
    }

    suspend fun removeFromWishList(movieId: Int){
        movieDao.deleteFromWishList(movieId)
    }

    suspend fun getWishlistDetails(): List<MovieDetails> {
        return movieDao.getWishlist()
    }

    suspend fun getSuggestedMovies():List<MovieDetails> {
        return movieDao.getSuggestedMovieDetails()
    }

    suspend fun searchMovies(query: String): List<MovieEntity> {
        return movieDao.searchMovies("%$query%")
    }

    fun isFavourite(movieId: Int):Boolean{
        return movieDao.isFavourite(movieId)
    }
}