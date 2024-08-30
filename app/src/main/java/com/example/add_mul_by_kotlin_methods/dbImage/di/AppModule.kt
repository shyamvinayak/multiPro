package com.example.add_mul_by_kotlin_methods.dbImage.di

import android.content.Context
import androidx.room.Room
import com.example.add_mul_by_kotlin_methods.dbImage.room.ImageDB
import com.example.add_mul_by_kotlin_methods.dbImage.room.ImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageAppModule {

    @Provides
    @Singleton
    fun provideImageDatabase(@ApplicationContext context: Context): ImageDB {
        return Room.databaseBuilder(
            context,
            ImageDB::class.java,
            "image.db"
        ).fallbackToDestructiveMigration(false).build()
    }

    @Provides
    fun provideImageDao(database: ImageDB): ImageDao {
        return database.imageDao()
    }
}