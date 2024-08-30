package com.example.add_mul_by_kotlin_methods.roomHiltRetro.remote

import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.MovieData
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