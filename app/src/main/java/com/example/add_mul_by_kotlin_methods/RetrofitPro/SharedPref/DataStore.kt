package com.example.add_mul_by_kotlin_methods.RetrofitPro.SharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.Data
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.convertDataToJson
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.convertJsonToData
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.convertJsonToList
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.convertListToJson


fun storeMovieList(context: Context, movies: List<Data>) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val jsonString = convertListToJson(movies)
    editor.putString("movie_list", jsonString)
    editor.apply() // or editor.commit()
}

fun retrieveMovieList(context: Context): List<Data>? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val jsonString = sharedPreferences.getString("movie_list", null)
    return jsonString?.let {
        convertJsonToList(it)
    }
}

fun storeMovieDetails(context: Context, movieId: String, data: Data) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val jsonString = convertDataToJson(data)
    editor.putString("movie_$movieId", jsonString)
    editor.apply() // or editor.commit()
}

fun retrieveMovieDetails(context: Context, movieId: String): Data? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val jsonString = sharedPreferences.getString("movie_$movieId", null)
    return jsonString?.let {
        convertJsonToData(it)
    }
}