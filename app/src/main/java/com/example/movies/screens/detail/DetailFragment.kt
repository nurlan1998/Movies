package com.example.movies.screens.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.models.DetailMovies
import com.example.movies.utils.Constants
import com.example.movies.utils.Constants.Companion.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Response

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewModel by viewModels<DetailViewModel>()
    var trailerAdapter = TrailerAdapter()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTrailer.adapter = trailerAdapter

        viewModel.getDetailMovie(args.id)
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                showDetail(it)
                Log.i("Responce", it.body()?.videos?.results.toString())
                val trailers = it.body()?.videos?.results
                if (trailers != null) trailerAdapter.trailers = trailers
            }
        })
    }

    private fun showDetail(detail: Response<DetailMovies>) {
        tvDetailTitle.text = detail.body()?.title
        tvReleaseDate.text = detail.body()?.releaseDate
        tvDetailOverView.text = detail.body()?.overview

        Glide.with(this).load(Constants.IMAGE_URL + detail.body()?.posterPath)
            .centerCrop()
            .into(ivDetailMovie)
    }
}