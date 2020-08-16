package com.example.movies.ui.home

import android.content.Intent
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
import com.example.movies.ui.detail.DetailActivity
import com.example.movies.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment() : Fragment(R.layout.fragment_home) {

    private val adapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setOnItemClickCallBack(object: HomeAdapter.OnItemClickCallBack{
            override fun onItemClicked(id: Int?) {
                Log.i("MovieId",id.toString())
                val intent = Intent(requireContext(),DetailActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }

        })

        recyclerView.adapter = adapter
        val itemViewModel = ViewModelProviders.of(this)
            .get(MovieViewModel::class.java)
        itemViewModel.moviePagedList.observe(requireActivity(), Observer {
            adapter.submitList(it)
        })
    }
}