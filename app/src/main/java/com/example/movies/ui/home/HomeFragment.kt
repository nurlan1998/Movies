package com.example.movies.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.liveData
import com.example.movies.R
import com.example.movies.data.model.MovieVoteAverage
import com.example.movies.data.model.PopularMovies
import com.example.movies.network.ApiInterFace
import com.example.movies.network.RetrofitInstance
import com.example.movies.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val adapter = HomeAdapter()
    lateinit var retrofitService: ApiInterFace

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
//        retrofitService = RetrofitInstance.getRetrofitInstance().create(ApiInterFace::class.java)
//        showData()
        val itemViewModel = ViewModelProviders.of(this)
            .get(MovieViewModel::class.java)
        itemViewModel.moviePagedList.observe(requireActivity(), Observer {
            adapter.submitList(it)
        })
    }

//
//    private fun showData() {
////        val responceLiveData: LiveData<Response<MovieVoteAverage>> = liveData {
////            val responce = retrofitService.getMovies(2)
////            emit(responce)
////        }
////        responceLiveData.observe(requireActivity(), Observer {
////            val movieList = it.body()
////                if (movieList != null) {
////                    adapter.models = movieList.results
////                }
////            Log.i("Result", movieList.toString())
////        })
//
//        retrofitService.getMovies(1).enqueue(object : Callback<MovieVoteAverage> {
//            override fun onFailure(call: Call<MovieVoteAverage>, t: Throwable) {
//                Log.e("Repository", "onFailure", t)
//            }
//
//            override fun onResponse(call: Call<MovieVoteAverage>, response: Response<MovieVoteAverage>) {
//                if(response.isSuccessful){
//                    val responseBody = response.body()
//                    if(responseBody != null){
//                        adapter.models = responseBody.results
//                    }else
//                        Log.d("Repository","Failed to get repository")
//                }
//            }
//
//        })
//    }
}