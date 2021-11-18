package com.example.movies.networking

import com.example.movies.models.MovieResponse

class RemoteApi(private val apiService: RemoteApiService) {
    suspend fun getMovies(): Result<MovieResponse> = try {
        val data = apiService.getMovies()
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }
}
