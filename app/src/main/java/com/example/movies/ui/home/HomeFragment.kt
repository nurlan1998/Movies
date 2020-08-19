package com.example.movies.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movies.R
import com.example.movies.ui.detail.DetailActivity
import com.example.movies.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment() : Fragment(R.layout.fragment_home) {

    private val adapter = HomeAdapter()

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