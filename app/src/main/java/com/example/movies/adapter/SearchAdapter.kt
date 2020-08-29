package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.model.DataMoviesResult
import kotlinx.android.synthetic.main.movie_item.view.*

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var models: List<DataMoviesResult> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.populate(models[position])
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun populate(dataMoviesResult: DataMoviesResult){
            itemView.tvTitle.text = dataMoviesResult.title
        }
    }
}