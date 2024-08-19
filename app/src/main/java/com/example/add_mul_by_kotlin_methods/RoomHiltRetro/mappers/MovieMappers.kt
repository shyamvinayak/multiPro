package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.mappers


import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.Movie
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.Entity.MovieEntity
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.remote.MovieDto
import com.google.gson.Gson


fun MovieDto.toMovieEntity(): MovieEntity {
    val castsJson = Gson().toJson(casts)
    return MovieEntity(
        movie_id = movie_id,
        original_title = original_title,
        original_language = original_language,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        backdrop_path = backdrop_path,
        release_date = release_date,
        vote_average = vote_average,
        vote_count = vote_count,
        adult = adult,
        casts = castsJson,
    )
}

fun MovieEntity.toMovie(): Movie {
    val castsJson = Gson().toJson(casts)
    return Movie(
        movie_id = movie_id,
        original_title = original_title,
        original_language = original_language,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        backdrop_path = backdrop_path,
        release_date = release_date,
        vote_average = vote_average,
        vote_count = vote_count,
        adult = adult,
        casts = castsJson,
    )
}

