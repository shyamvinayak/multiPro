package com.example.add_mul_by_kotlin_methods.RetrofitPro.model

data class MovieHomeModel(
    val `data`: List<Data>,
    val next_cursor: String,
    val next_page_url: String,
    val path: String,
    val per_page: Int,
    val prev_cursor: Any,
    val prev_page_url: Any
)