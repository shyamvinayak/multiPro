package com.example.add_mul_by_kotlin_methods.RetrofitPro.model


import com.example.add_mul_by_kotlin_methods.RetrofitPro.RoomDB.MovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Data(
    val adult: Int,
    val backdrop_path: String,
    val casts: List<CastX>,
    val created_at: Any,
    val id: String,
    val movie_id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val updated_at: Any,
    val vote_average: Double,
    val vote_count: Int
)

fun Data.toMovieEntity(): MovieEntity {
    // Convert List<CastX> to JSON String
    val castsJson = Gson().toJson(casts)
    return MovieEntity(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        casts = castsJson, // Assuming you have a type converter for this field
        created_at = "${this.created_at}" , // Adjust if needed
        movie_id = this.movie_id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        updated_at =  "${this.updated_at}", // Adjust if needed
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}


// Convert Data object to JSON
fun convertDataToJson(data: Data): String {
    return Gson().toJson(data)
}

// Convert JSON back to Data object
fun convertJsonToData(json: String): Data {
    return Gson().fromJson(json, Data::class.java)
}

fun convertListToJson(movies: List<Data>): String {
    val gson = Gson()
    val type = object : TypeToken<List<Data>>() {}.type
    return gson.toJson(movies, type)
}

fun convertJsonToList(json: String): List<Data> {
    val gson = Gson()
    val type = object : TypeToken<List<Data>>() {}.type
    return gson.fromJson(json, type)
}