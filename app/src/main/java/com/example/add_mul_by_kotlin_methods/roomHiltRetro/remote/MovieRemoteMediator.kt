package com.example.add_mul_by_kotlin_methods.roomHiltRetro.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.MovieDatabase
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.MovieEntity
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.mappers.toMovieEntity
import okio.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieDatabase: MovieDatabase,
    private val movieApi: MovieApi
): RemoteMediator<Int, MovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try{
            val loadkey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null){
                        1
                    }else{
                        (lastItem.autoGenId / state.config.pageSize)+1
                    }
                }
            }
            val movies = movieApi.getMovies(
                page = loadkey
            )
            movieDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    movieDatabase.dao.clearAll()
                }
                val movieEntities = movies.data.map{it.toMovieEntity()}
                movieDatabase.dao.upsertAll(movieEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = movies.data.isEmpty()
            )
        }catch (e:IOException){
            println("IOException:--$e")
            MediatorResult.Error(e)
        }catch (e:HttpException){
            println("HttpException:--$e")
            MediatorResult.Error(e)
        }
    }
}