package com.example.movies.screens.popular

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.utils.GridSpacingItemDecoration
import com.example.movies.adapter.MoviesPagingAdapter
import com.example.movies.screens.home.HomeFragment
import com.example.movies.screens.upcoming.UpcomingViewModel
import com.example.movies.utils.Constants.Companion.MOVIE_ID
import com.example.movies.utils.Constants.Companion.SORT_CRITERIA_POP
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_popular.*

@AndroidEntryPoint
class PopularFragment : Fragment(R.layout.fragment_popular) {

    lateinit var adapter: MoviesPagingAdapter
    private val viewModel by viewModels<PopularViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        adapter.setOnItemClickCallBack(object : MoviesPagingAdapter.OnItemClickCallBack {
            override fun onItemClicked(id: Int?) {
                val mAction = id?.let { PopularFragmentDirections.actionPopularFragmentToDetailFragment2(it) }
                findNavController().navigate(mAction!!)
            }
        })

        viewModel.popularMovie(SORT_CRITERIA_POP)
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        })
    }

    private fun setupRecyclerView(){
        adapter = MoviesPagingAdapter()
        rvPopular.adapter = adapter

        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvPopular.layoutManager = GridLayoutManager(requireContext(), 3)
            rvPopular.addItemDecoration(
                GridSpacingItemDecoration(
                    3,
                    0,
                    false
                )
            )
        } else {
            rvPopular.layoutManager = GridLayoutManager(requireContext(), 3)
            rvPopular.addItemDecoration(
                GridSpacingItemDecoration(
                    3,
                    10,
                    false
                )
            )
        }
    }
}