package com.example.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.App
import com.example.movies.R
import com.example.movies.networking.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val remoteApi = App.remoteApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data = ArrayList<ItemsViewModel>()
        val adapter = CustomAdapter(data)

        val reclyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        reclyclerView.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch(Dispatchers.Main) {
            val result = remoteApi.getMoviesByPage(1)
            if (result is Success) {
                result.data.total_results.toString()
                result.data.total_pages.toString()
                for (item in result.data.results) {
                    data.add(ItemsViewModel(item.backdrop_path, item.title, item.release_date))
                }
                adapter.notifyDataSetChanged()
            }
        }
        reclyclerView.adapter = adapter
    }
}

