package com.example.movies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
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

        var progressValue = (items.rating * 10).toInt()

        holder.name.text = items.name
        holder.date.text = items.date
        val picasso = Picasso.get()
        picasso.load(img_URL_Backdrop + items.image)
            .into(holder.image)
        holder.progressBar.progress = progressValue
        holder.progressTextIndicator.text = "$progressValue%"
    }
    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var image: ImageView = itemView.findViewById(R.id.img_movie)
        var name: TextView = itemView.findViewById(R.id.txt_movie_name)
        var date: TextView = itemView.findViewById(R.id.txt_movie_date)
        var progressBar: ProgressBar = itemView.findViewById(R.id.circular_determinative_pb)
        var progressTextIndicator: TextView = itemView.findViewById(R.id.progress_tv)

    }

}