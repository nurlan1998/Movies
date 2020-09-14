package com.example.movies.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.model.VideosResult
import com.example.movies.utils.Constants.Companion.VIDEO_URL
import kotlinx.android.synthetic.main.trailer_item.view.*

class TrailerAdapter : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {

    var trailers: List<VideosResult> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.trailer_item, parent, false)
        return TrailerViewHolder(view)
    }

    override fun getItemCount(): Int = trailers.size

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.populate(trailers[position])
    }

    inner class TrailerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populate(trailer: VideosResult) {
            itemView.title.text = trailer.name
            itemView.setOnClickListener {
                val url = Uri.parse(VIDEO_URL + "${trailer.key}")
                itemView.context.startActivity(Intent(Intent.ACTION_VIEW, url))
            }
        }
    }
}