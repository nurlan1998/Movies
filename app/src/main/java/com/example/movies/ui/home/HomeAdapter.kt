package com.example.movies.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movies.R
import com.example.movies.data.model.Result
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var models: List<Result> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    //"https://image.tmdb.org/t/p/w342

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.populate(models[position])
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun populate(dataMovie: Result){
            itemView.tvTitle.text = dataMovie.title

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${dataMovie.posterPath}")
                .transform(CenterCrop())
                .into(itemView.ivMovie)
        }
    }
}