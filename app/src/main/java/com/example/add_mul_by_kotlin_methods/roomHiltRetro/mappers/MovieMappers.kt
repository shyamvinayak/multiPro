package com.example.add_mul_by_kotlin_methods.roomHiltRetro.mappers


import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.Movie
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.MovieEntity
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.remote.MovieDto
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
        casts = casts,
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

