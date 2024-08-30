package com.example.add_mul_by_kotlin_methods.roomHiltRetro.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.Cast

@Entity
data class MovieDto(
    @PrimaryKey
    val autoGenId:Int=0,
    val id:String,
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
    val casts:List<Cast>,
    val stringId:String?,
    var isFavorite: Boolean = false,

)