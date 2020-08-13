package com.example.movies.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movies.R
import com.example.movies.data.model.MoviesVoteResult
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeAdapter: PagedListAdapter<MoviesVoteResult,HomeAdapter.HomeViewHolder>(USER_COMPARATOR) {
//
//    var models: List<MoviesVoteResult> = listOf()
//    set(value) {
//        field = value
//        notifyDataSetChanged()
//    }

    //"https://image.tmdb.org/t/p/w342

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
            val movie =getItem(position)
            movie?.let { holder.populate(movie) }
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun populate(dataMovie: MoviesVoteResult){
            itemView.tvTitle.text = dataMovie.title

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${dataMovie.posterPath}")
                .transform(CenterCrop())
                .into(itemView.ivMovie)
        }
    }
    companion object{
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<MoviesVoteResult>(){
            override fun areItemsTheSame(
                oldItem: MoviesVoteResult,
                newItem: MoviesVoteResult
            ): Boolean = oldItem.id == newItem.id

            @SuppressLint("...DifUtilsEquals")
            override fun areContentsTheSame(
                oldItem: MoviesVoteResult,
                newItem: MoviesVoteResult): Boolean = newItem == oldItem
        }
    }
}