package com.example.movies.networking
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
const val BASE_URL = "https://api.themoviedb.org/3/movie/"
fun buildClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

fun buildRetrofit(): Retrofit {
  val jsonFactory = Json{ignoreUnknownKeys=true}
                      .asConverterFactory( "application/json".toMediaType())
  return Retrofit.Builder()
      .client(buildClient())
      .baseUrl(BASE_URL)
      .addConverterFactory(jsonFactory)
      .build()
}

fun buildApiService(): RemoteApiService = buildRetrofit().create(RemoteApiService::class.java)