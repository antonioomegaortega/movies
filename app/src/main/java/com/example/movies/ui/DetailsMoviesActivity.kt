package com.example.movies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.R

class DetailsMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movies)
        val extras = intent.extras
        var name = extras?.getString("title")
        var date = extras?.getString("date")
        var overview = extras?.getString("overview")
        var img = extras?.getString("img")
        print("")
    }
}