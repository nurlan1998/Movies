package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.DataMoviesResult
import com.example.movies.utils.Constants.Companion.IMAGE_URL
import kotlinx.android.synthetic.main.search_item.view.*

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var models: List<DataMoviesResult> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClick: (movieId: Int) -> Unit = {}

    fun setOnItemClickListener(onItemClick: (movieId: Int) -> Unit) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.populate(models[position])
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populate(dataMoviesResult: DataMoviesResult) {
            itemView.tvSearchTitle.text = dataMoviesResult.title
            Glide.with(itemView.context)
                .load(IMAGE_URL + dataMoviesResult.posterPath)
                .centerCrop()
                .into(itemView.ivSearchMovie)

            itemView.setOnClickListener {
                onItemClick.invoke(dataMoviesResult.id)
            }
        }
    }
}