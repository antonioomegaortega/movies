package com.example.movies.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.R
import com.squareup.picasso.Picasso

class DetailsMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movies)
        val extras = intent.extras
        var name = extras?.getString("title")
        var date = extras?.getString("date")
        var overview = extras?.getString("overview")
        var img = extras?.getString("img")
        var txt_name = findViewById<TextView>(R.id.txt_detail_name)
        var txt_date = findViewById<TextView>(R.id.txt_detail_date)
        var img_picture = findViewById<ImageView>(R.id.img_detailMovie)
        var txt_overview = findViewById<TextView>(R.id.txt_detail_overview)

        var img_URL_Backdrop = "https://image.tmdb.org/t/p/w300"
        val picasso = Picasso.get()
        picasso.load(img_URL_Backdrop + img)
            .into(img_picture)
        txt_name.text = name
        txt_date.text = date
        txt_overview.text = overview


        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}