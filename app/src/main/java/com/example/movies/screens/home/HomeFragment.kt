package com.example.movies.screens.home

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.utils.GridSpacingItemDecoration
import com.example.movies.adapter.MoviesPagingAdapter
import com.example.movies.screens.detail.DetailFragment
import com.example.movies.utils.Constants.Companion.MOVIE_ID
import com.example.movies.utils.Constants.Companion.SORT_CRITERIA_TOP
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var adapter: MoviesPagingAdapter
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.homeMovie(SORT_CRITERIA_TOP)

        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.setOnItemClickCallBack(object : MoviesPagingAdapter.OnItemClickCallBack {
            override fun onItemClicked(id: Int?) {

                val action = id?.let {
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment2(it) }
                findNavController().navigate(action!!)
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = MoviesPagingAdapter()
        recyclerView.adapter = adapter

        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            recyclerView.addItemDecoration(
                GridSpacingItemDecoration(
                    3,
                    0,
                    false
                )
            )
        } else {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            recyclerView.addItemDecoration(
                GridSpacingItemDecoration(
                    3,
                    10,
                    true
                )
            )
        }
    }
}