package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.movies.networking.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val remoteApi = App.remoteApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text_pages = findViewById<TextView>(R.id.txt_pages)
        val text_total = findViewById<TextView>(R.id.txt_total)

        GlobalScope.launch(Dispatchers.Main) {
            val result = remoteApi.getMoviesByPage(1)
            if (result is Success) {
                text_pages.text = "Movies Available: " + result.data.total_results.toString()
                text_total.text = "Pages: " + result.data.total_pages.toString()

            }
        }
    }
}