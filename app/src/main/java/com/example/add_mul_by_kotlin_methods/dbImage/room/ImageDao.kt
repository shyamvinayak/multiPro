package com.example.add_mul_by_kotlin_methods.dbImage.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(imageEntity: ImageEntity)

    @Query("SELECT * FROM images")
    fun getAllImage(): LiveData<List<ImageEntity>>

    @Query("DELETE FROM images WHERE id = :imageId")
    fun deleteImage(imageId:Int)
}