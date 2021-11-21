package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.networking.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val remoteApi = App.remoteApi
    val data = ArrayList<ItemsViewModel>()
    var img_URL_Backdrop = "https://image.tmdb.org/t/p/w300"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch(Dispatchers.Main) {
            val result = remoteApi.getMovies()
            if (result is Success) {
                result.data.results.forEach {
                    data.add(ItemsViewModel(img_URL_Backdrop + it.backdrop_path, it.title, it.release_date))
                    recyclerview.adapter = MoviesAdapter(data)
                }
            }
        }
    }
}