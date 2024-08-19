package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.Cast
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.Link
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.Movie

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

)