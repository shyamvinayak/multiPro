package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.remote

import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.MovieData
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {
    @GET("paginated")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MovieData

    companion object {
        const val BASE_URL = "https://jsonfakery.com/movies/"

    }
}