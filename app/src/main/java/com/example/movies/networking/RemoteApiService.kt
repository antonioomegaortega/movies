package com.example.movies.networking
import com.example.movies.models.*
import retrofit2.http.*

interface RemoteApiService {

    @Throws(Exception::class)
    @GET("popular?language=en-US&api_key=55957fcf3ba81b137f8fc01ac5a31fb5")
    suspend fun getMovies():MovieResponse

    @Throws(Exception::class)
    @GET("popular")
    suspend fun getMoviesByPage(@Query("page") page:Int,
                                @Query("api_key") api_key:String,
                                @Query("language") language:String):MovieResponse

    @Throws(Exception::class)
    @GET("now_playing")
    suspend fun getMoviesPlayingNow(@Query("api_key") api_key:String,
                                    @Query("language") language:String):MovieResponse

}

