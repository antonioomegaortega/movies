package com.example.movies.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.App
import com.example.movies.R
import com.example.movies.models.Movie
import com.example.movies.networking.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),MyCustomObjectListener {
    private val remoteApi = App.remoteApi
    var list = ArrayList<Movie>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data = ArrayList<ItemsViewModel>()
        val adapter = CustomAdapter(data)

        adapter.setCallBackListener(object : MyCustomObjectListener {
            override fun onClickMovie(id:Int) {
                var movieSelected = list.get(id)
                val intent = Intent(applicationContext, DetailsMoviesActivity::class.java)
                intent.putExtra("title", movieSelected.title)
                intent.putExtra("date", movieSelected.release_date)
                intent.putExtra("overview", movieSelected.overview)
                intent.putExtra("img", movieSelected.backdrop_path)
                startActivity(intent)
            }
        })

        val reclyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        reclyclerView.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch(Dispatchers.Main) {
            val result = remoteApi.getMoviesByPage(1)
            if (result is Success) {
                list = result.data.results as ArrayList<Movie>
                for (item in list) {
                    data.add(ItemsViewModel(item.backdrop_path, item.title, item.release_date, item.vote_average))
                }
                adapter.notifyDataSetChanged()
            }
        }
        reclyclerView.adapter = adapter
    }

    override fun onClickMovie(id:Int) {

    }
}

interface MyCustomObjectListener {
    fun onClickMovie(id:Int)
}

