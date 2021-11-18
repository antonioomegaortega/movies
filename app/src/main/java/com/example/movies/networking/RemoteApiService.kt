package com.example.movies.networking
import com.example.movies.models.*
import retrofit2.http.*

interface RemoteApiService {

    @Throws(Exception::class)
    @GET("popular?language=en-US&api_key=55957fcf3ba81b137f8fc01ac5a31fb5")
    suspend fun getMovies():MovieResponse

}

