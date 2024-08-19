package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain

import androidx.room.Embedded
import androidx.room.Relation
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.Entity.CastEntity
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.Entity.MovieEntity
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.Entity.VoteEntity
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.local.Entity.WishlistEntity

data class MovieDetails (
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "movieId"
    )
    val wishlist:WishlistEntity?,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "movieId"
    )
    val cast:CastEntity?,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "movieId"
    )
    val vote:List<VoteEntity>

)