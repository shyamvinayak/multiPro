package com.example.add_mul_by_kotlin_methods.dbImage.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ImageEntity::class], version = 3, exportSchema = false)
abstract class ImageDB :RoomDatabase(){
    abstract fun imageDao():ImageDao
}