package com.example.add_mul_by_kotlin_methods.RetrofitPro.RoomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.Data

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: MovieEntity)

    @Query("SELECT * FROM movie_entity")
    fun getAllMovies(): List<Data>

    @Query("SELECT * FROM movie_entity WHERE movie_id =:movieId")
    suspend fun getMovieById(movieId: Int): Data?

    @Query("DELETE FROM movie_entity")
    suspend fun deleteAll()

}
