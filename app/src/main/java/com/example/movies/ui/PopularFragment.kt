package com.example.movies.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movies.R
import com.example.movies.adapter.MoviesAdapter
import com.example.movies.utils.Constants.Companion.MOVIE_ID
import com.example.movies.utils.Constants.Companion.SORT_CRITERIA_POP
import com.example.movies.viewmodel.MovieViewModel
import com.example.movies.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment(R.layout.fragment_popular) {

    private val adapter = MoviesAdapter()
    private var sort_criteria = SORT_CRITERIA_POP

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setOnItemClickCallBack(object : MoviesAdapter.OnItemClickCallBack {
            override fun onItemClicked(id: Int?) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(MOVIE_ID, id)
                startActivity(intent)
            }

        })

        rvPopular.adapter = adapter
        val itemViewModel = ViewModelProviders.of(this, MovieViewModelFactory(sort_criteria))
            .get(MovieViewModel::class.java)
        itemViewModel.moviePagedList.observe(requireActivity(), Observer {
            adapter.submitList(it)
        })
    }
//        retrofitService = RetrofitInstance.getRetrofitInstance().create(ApiInterFace::class.java)
//        rvPopular.adapter = adapter
//        showPopular()
//    }
//
//    private fun showPopular() {
//        retrofitService.getPopularMovies().enqueue(object : Callback<MovieVoteAverage> {
//            override fun onFailure(call: Call<MovieVoteAverage>, t: Throwable) {
//
//            }
//
//            override fun onResponse(
//                call: Call<MovieVoteAverage>,
//                response: Response<MovieVoteAverage>
//            ) {
//                val responseBody = response.body()!!
//                adapter.models = responseBody.results
//            }
//        })
//    }
}