package com.example.movies.screens.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.models.DetailMovies
import com.example.movies.utils.Constants.Companion.MOVIE_ID
import com.example.movies.utils.Constants.Companion.IMAGE_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Response

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailViewModel>()
    var trailerAdapter = TrailerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = intent.getIntExtra(MOVIE_ID, 0)

        rvTrailer.adapter = trailerAdapter

        viewModel.getDetailMovie(id)
        viewModel.movieLiveData.observe(this, Observer {
                if (it.isSuccessful) {
                    showDetail(it)
                    Log.i("Responce", it.body()?.videos?.results.toString())
                    val trailers = it.body()?.videos?.results
                    if (trailers != null) trailerAdapter.trailers = trailers
                }
            })
    }

    fun showDetail(detail: Response<DetailMovies>) {
        tvDetailTitle.text = detail.body()?.title
        tvReleaseDate.text = detail.body()?.releaseDate
        tvDetailOverView.text = detail.body()?.overview

        Glide.with(this).load(IMAGE_URL + detail.body()?.posterPath)
            .centerCrop()
            .into(ivDetailMovie)
    }
}