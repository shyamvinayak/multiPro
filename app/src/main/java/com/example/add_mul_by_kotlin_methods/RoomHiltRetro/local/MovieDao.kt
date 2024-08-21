package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local

import androidx.lifecycle.LiveData
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(movies:List<MovieEntity>) //Update and Insert Movie Details

    @Query("SELECT * FROM movieentity")
    fun pagingSource():PagingSource<Int, MovieEntity> //Show Movie List by Infinite Scroll

    @Query("DELETE FROM movieentity")
    suspend fun clearAll() //Delete Movie from Room

    @Query("SELECT * FROM movieentity WHERE movie_id =:movieId ")
    suspend fun loadSingleMovie(movieId:Int):List<MovieEntity> //Select Single Movie by movie_Id

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWishList(wishlistEntity: WishlistEntity):Long  //add movie to WishList

    @Query("SELECT * FROM wishlistentity WHERE movieId = :movieId")
    suspend fun isMovieInWishlist(movieId: Int): WishlistEntity? //Get wishlist Boolean value

    @Query("DELETE FROM wishlistentity WHERE movieId = :movieId")
    suspend fun deleteFromWishList(movieId: Int) //Delete existing movie from wishlist by movie_Id

    @Query("SELECT isFavorite FROM movieentity WHERE movie_id = :movieId")
    fun isFavourite(movieId: Int):Boolean //Get wishlist Boolean value

    @Query("""
        SELECT movieentity.*, 
               wishlistentity.movieId AS wishlistId
         FROM movieentity
         LEFT JOIN wishlistentity ON movieentity.movie_id = wishlistentity.movieId 
        WHERE wishlistentity.movieId IS NOT NULL
        """)
    suspend fun getWishlist():List<MovieDetails>// get WishList Movies

    /*@Query("""
        SELECT m.*
        FROM movieentity m
        WHERE EXISTS (
         SELECT 1
            FROM wishlistentity w
            INNER JOIN castentity c ON m.movie_id = c.movieId
            LEFT JOIN voteentity v ON m.movie_id = v.movieId
        )
    """)*/

    @Query("""
        SELECT m.*
        FROM movieentity m
        WHERE EXISTS(
        SELECT 1
            FROM wishlistentity w
            LEFT JOIN castentity c ON m.movie_id = c.movieId
            LEFT JOIN voteentity v ON m.movie_id = v.movieId
        )
    """

    )
    suspend fun getSuggestedMovieDetails(): List<MovieDetails>  //Get Suggested Movies Based on Wishlist data-cast,vote

}