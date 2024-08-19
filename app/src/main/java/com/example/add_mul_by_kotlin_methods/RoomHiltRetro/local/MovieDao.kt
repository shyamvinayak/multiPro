package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.MovieDetails
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.Entity.MovieEntity
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.Entity.WishlistEntity

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertAll(movies:List<MovieEntity>)

    @Query("SELECT * FROM movieentity")
    fun pagingSource():PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movieentity")
    suspend fun clearAll()

    @Query("SELECT * FROM movieentity WHERE movie_id =:movieId ")
    suspend fun loadSingleMovie(movieId:Int):List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWishList(wishlistEntity: WishlistEntity):Long

    @Query("SELECT * FROM wishlistentity WHERE movieId = :movieId")
    suspend fun isMovieInWishlist(movieId: Int): WishlistEntity?

    @Query("DELETE FROM wishlistentity WHERE movieId = :movieId")
    suspend fun deleteFromWishList(movieId: Int)

    @Query("""
        SELECT movieentity.*, 
               wishlistentity.id AS wishlistId, 
               castentity.name AS castName, 
               voteentity.voteRange, 
               voteentity.voteCount 
        FROM movieentity
        LEFT JOIN wishlistentity ON movieentity.movie_id = wishlistentity.movieId 
        LEFT JOIN castentity ON movieentity.movie_id = castentity.movieId
        LEFT JOIN voteentity ON movieentity.movie_id = voteentity.movieId 
        WHERE wishlistentity.id IS NOT NULL
    """)
    suspend fun getWishlistDetails(): List<MovieDetails>

}