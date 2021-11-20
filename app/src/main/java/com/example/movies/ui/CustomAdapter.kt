package com.example.movies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.squareup.picasso.Picasso

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(getAdapterPosition(), getItemViewType())
    }
    return this
}

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    var img_URL_Backdrop = "https://image.tmdb.org/t/p/w300"
    var listener: MyCustomObjectListener? = null

    fun setCallBackListener(listener:MyCustomObjectListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view).listen { pos, type ->
            val item = mList.get(pos)
            listener?.onClickMovie(pos)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = mList[position]
        holder.name.text = items.name
        holder.date.text = items.date
        val picasso = Picasso.get()
        picasso.load(img_URL_Backdrop + items.image)
            .into(holder.image)
    }
    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var image: ImageView = itemView.findViewById(R.id.img_movie)
        var name: TextView = itemView.findViewById(R.id.txt_movie_name)
        var date: TextView = itemView.findViewById(R.id.txt_movie_date)
    }

}