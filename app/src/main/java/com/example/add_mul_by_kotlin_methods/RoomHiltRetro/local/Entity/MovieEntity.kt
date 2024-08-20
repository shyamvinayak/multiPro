package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val autoGenId:Int = 0,
    val movie_id:Int,
    val original_title:String,
    val original_language:String,
    val overview:String,
    val popularity:Double,
    val poster_path:String,
    val backdrop_path:String,
    val release_date:String,
    val vote_average:Double,
    val vote_count:Double,
    val adult:Double,
    val casts: String,
    var isFavorite: Boolean = false,

)
@Entity
data class WishlistEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val movieId:Int,
    val isFavorite: Boolean
)

@Entity
data class CastEntity(
    @PrimaryKey(autoGenerate = true) val id:Int =0,
    val movieId:Int,
    val name:String
)

@Entity
data class VoteEntity(
    @PrimaryKey val movieId:Int,
    val voteRange:Double,
    val voteCount:Double
)
