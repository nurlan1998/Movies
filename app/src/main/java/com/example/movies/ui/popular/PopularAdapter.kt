package com.example.movies.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movies.R
import com.example.movies.data.model.MoviesVoteResult
import kotlinx.android.synthetic.main.movie_item.view.*

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    var models: List<MoviesVoteResult> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return PopularViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.populate(models[position])
    }

    inner class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun populate(moviesVoteResult: MoviesVoteResult){
            itemView.tvTitle.text = moviesVoteResult.title
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/original${moviesVoteResult.posterPath}")
                .transform(CenterCrop())
                .into(itemView.ivMovie)
        }
    }
}