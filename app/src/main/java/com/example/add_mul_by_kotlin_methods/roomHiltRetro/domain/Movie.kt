package com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain

data class Movie(
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
    val casts:String,
    var isFavorite: Boolean = false,


)
