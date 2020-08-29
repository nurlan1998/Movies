package com.example.movies.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.movies.R
import com.example.movies.adapter.SearchAdapter
import com.example.movies.data.model.DataMovies
import com.example.movies.network.ApiInterFace
import com.example.movies.network.RetrofitInstance
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment(R.layout.fragment_search) {

    var searchAdapter = SearchAdapter()
    lateinit var retrofitService: ApiInterFace

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSearch.adapter = searchAdapter

        retrofitService = RetrofitInstance.getRetrofitInstance().create(ApiInterFace::class.java)
        showPopular()
    }

    private fun showPopular() {
                retrofitService.getSearchMovies("power").enqueue(object : Callback<DataMovies> {
                    override fun onFailure(call: Call<DataMovies>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<DataMovies>,
                        response: Response<DataMovies>
                    ) {
                        val responseBody = response.body()!!
                        searchAdapter.models = responseBody.results
                        Log.i("clicked", responseBody.results.toString())
                    }
                })
            }
}