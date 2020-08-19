package com.example.movies.ui.popular

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movies.R
import com.example.movies.data.model.MovieVoteAverage
import com.example.movies.network.ApiInterFace
import com.example.movies.network.RetrofitInstance
import kotlinx.android.synthetic.main.fragment_popular.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularFragment : Fragment(R.layout.fragment_popular) {

    lateinit var retrofitService: ApiInterFace
    var adapter = PopularAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitService = RetrofitInstance.getRetrofitInstance().create(ApiInterFace::class.java)
        rvPopular.adapter = adapter
        showPopular()
    }

    private fun showPopular() {
        retrofitService.getPopularMovies().enqueue(object : Callback<MovieVoteAverage> {
            override fun onFailure(call: Call<MovieVoteAverage>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MovieVoteAverage>,
                response: Response<MovieVoteAverage>
            ) {
                val responseBody = response.body()!!
                adapter.models = responseBody.results
            }
        })
    }
}