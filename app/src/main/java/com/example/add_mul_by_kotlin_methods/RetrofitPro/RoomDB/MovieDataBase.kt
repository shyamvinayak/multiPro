package com.example.add_mul_by_kotlin_methods.RetrofitPro.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.add_mul_by_kotlin_methods.RetrofitPro.Converter.CastXTypeConverter
import com.example.add_mul_by_kotlin_methods.RetrofitPro.Converter.DateTypeConverter

@Database(entities = [MovieEntity::class], version = 1,exportSchema = false)
@TypeConverters(CastXTypeConverter::class, DateTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao():MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

