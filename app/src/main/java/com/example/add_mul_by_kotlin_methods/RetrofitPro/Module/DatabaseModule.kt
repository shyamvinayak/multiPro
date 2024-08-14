package com.example.add_mul_by_kotlin_methods.RetrofitPro.Module


import android.app.Application
import com.example.add_mul_by_kotlin_methods.RetrofitPro.RoomDB.MovieDao
import com.example.add_mul_by_kotlin_methods.RetrofitPro.RoomDB.MovieDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return MovieDatabase.getDatabase(app)
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }
}