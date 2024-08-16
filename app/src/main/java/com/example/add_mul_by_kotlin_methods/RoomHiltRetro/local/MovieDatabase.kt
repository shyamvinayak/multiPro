package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.add_mul_by_kotlin_methods.RetrofitPro.Converter.CastXTypeConverter

@Database(
    entities = [MovieEntity::class],
    version = 2,
    exportSchema = false,
)
@TypeConverters(CastXTypeConverter::class)
abstract class MovieDatabase:RoomDatabase() {
    abstract val dao:MovieDao
}
