package com.example.movies.models
import kotlinx.serialization.Serializable
@Serializable
class MovieResponse(
        val page:Int,
        val results:List<Movie>,
        val total_pages:Int,
        val total_results:Int
)