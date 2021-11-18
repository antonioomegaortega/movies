package com.example.movies

import android.app.Application
import com.example.movies.networking.RemoteApi
import com.example.movies.networking.buildApiService


class App : Application() {
    companion object {
        private lateinit var instance: App
        private val apiService by lazy { buildApiService() }
        val remoteApi by lazy { RemoteApi(apiService) }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}