package com.example.add_mul_by_kotlin_methods.roomHiltRetro.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.add_mul_by_kotlin_methods.RetrofitPro.Converter.CastXTypeConverter
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.CastEntity
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.MovieEntity
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.VoteEntity
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.WishlistEntity

@Database(
    entities = [MovieEntity::class, WishlistEntity::class, CastEntity::class, VoteEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(CastXTypeConverter::class)
abstract class MovieDatabase:RoomDatabase() {
    abstract val dao:MovieDao
}
