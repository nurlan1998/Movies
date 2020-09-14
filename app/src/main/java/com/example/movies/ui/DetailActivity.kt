package com.example.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.adapter.TrailerAdapter
import com.example.movies.data.model.DetailMovies
import com.example.movies.repository.DetailRepository
import com.example.movies.utils.Constants.Companion.MOVIE_ID
import com.example.movies.utils.Constants.Companion.IMAGE_URL
import com.example.movies.viewmodel.MovieViewModel
import com.example.movies.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    var trailerAdapter = TrailerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = intent.getIntExtra(MOVIE_ID, 0)

        rvTrailer.adapter = trailerAdapter

        val repository = DetailRepository()
        val viewModelFactory = MovieViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        viewModel.getDetailMovie(id)
        viewModel.movieLiveData.observe(this, Observer {
            if (it.isSuccessful) {
                showDetail(it)
                Log.i("Responce", it.body()?.videos?.results.toString())
                var trailers = it.body()?.videos?.results
                if (trailers != null) {
                    trailerAdapter.trailers = trailers
                }
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