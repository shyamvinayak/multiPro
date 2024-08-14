package com.example.add_mul_by_kotlin_methods.RetrofitPro.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.CastX
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.Data

@Entity(tableName = "movie_entity")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "adult")val adult: Int,
    @ColumnInfo(name = "backdrop_path")val backdrop_path: String,
    @ColumnInfo(name = "casts")val casts: String, // Assuming casts will be serialized to a JSON string
    @ColumnInfo(name = "created_at")val created_at: String?, // Nullable field
    @ColumnInfo(name = "movie_id")val movie_id: Int,
    @ColumnInfo(name = "original_language")val original_language: String,
    @ColumnInfo(name = "original_title")val original_title: String,
    @ColumnInfo(name = "overview")val overview: String,
    @ColumnInfo(name = "popularity")val popularity: Double,
    @ColumnInfo(name = "poster_path")val poster_path: String,
    @ColumnInfo(name = "release_date")val release_date: String,
    @ColumnInfo(name = "updated_at")val updated_at: String?, // Nullable field
    @ColumnInfo(name = "vote_average")val vote_average: Double,
    @ColumnInfo(name = "vote_count")val vote_count: Int,
    @ColumnInfo(name = "new_column")val new_column: Int = 0


)