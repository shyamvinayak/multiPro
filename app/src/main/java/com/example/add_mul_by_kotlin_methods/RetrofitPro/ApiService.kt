package com.example.add_mul_by_kotlin_methods.RetrofitPro


import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.CommonDataItem
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.MovieHomeModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("random/1")
    suspend fun getSingleMovie(): List<CommonDataItem>

    @GET("infinite-scroll")
    suspend fun getMovieList(): MovieHomeModel

    @GET("paginated")
    suspend fun getMovies(@Query("page") page: Int): MovieHomeModel


}