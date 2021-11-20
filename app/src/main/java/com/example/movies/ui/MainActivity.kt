package com.example.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.App
import com.example.movies.R

class MainActivity : AppCompatActivity() {
    private val remoteApi = App.remoteApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val reclyclerView = findViewById<RecyclerView>(R.id.recyclerView)

    }
}

