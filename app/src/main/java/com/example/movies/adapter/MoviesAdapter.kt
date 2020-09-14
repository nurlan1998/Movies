package com.example.movies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.DataMoviesResult
import com.example.movies.utils.Constants.Companion.IMAGE_URL
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter : PagedListAdapter<DataMoviesResult, MoviesAdapter.HomeViewHolder>(
    USER_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.populate(movie)
        }
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populate(dataMovie: DataMoviesResult) {
            itemView.tvTitle.text = dataMovie.title

            Glide.with(itemView.context)
                .load(IMAGE_URL + dataMovie.posterPath)
                .into(itemView.ivMovie)
            itemView.setOnClickListener {
                onItemClickCallBack?.onItemClicked(dataMovie.id)
            }
        }
    }

    private var onItemClickCallBack: OnItemClickCallBack? = null

    interface OnItemClickCallBack {
        fun onItemClicked(id: Int?)
    }

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<DataMoviesResult>() {
            override fun areItemsTheSame(
                oldItem: DataMoviesResult,
                newItem: DataMoviesResult
            ): Boolean = oldItem.id == newItem.id

            @SuppressLint("...DifUtilsEquals")
            override fun areContentsTheSame(
                oldItem: DataMoviesResult,
                newItem: DataMoviesResult
            ): Boolean = newItem == oldItem
        }
    }
}