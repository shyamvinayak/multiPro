package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertAll(movies:List<MovieEntity>)

    @Query("SELECT * FROM movieentity")
    fun pagingSource():PagingSource<Int,MovieEntity>

    @Query("DELETE FROM movieentity")
    suspend fun clearAll()

    @Query("SELECT * FROM movieentity WHERE movie_id =:movieId ")
    suspend fun loadSingleMovie(movieId:Int):List<MovieEntity>

}