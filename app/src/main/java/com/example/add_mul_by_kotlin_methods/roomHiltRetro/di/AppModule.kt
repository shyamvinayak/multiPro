package com.example.add_mul_by_kotlin_methods.roomHiltRetro.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.MovieDao
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.MovieDatabase
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.MovieEntity
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.remote.MovieApi
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.remote.MovieRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    "movie.db"
                ).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideMoviePager(movieDb: MovieDatabase, moveApi: MovieApi): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MovieRemoteMediator(
                movieDatabase = movieDb,
                movieApi = moveApi
            ),
            pagingSourceFactory = {
                movieDb.dao.pagingSource()
            }
        )
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.dao
    }
}