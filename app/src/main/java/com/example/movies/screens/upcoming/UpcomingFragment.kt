package com.example.movies.screens.upcoming

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.utils.GridSpacingItemDecoration
import com.example.movies.adapter.MoviesPagingAdapter
import com.example.movies.screens.detail.DetailActivity
import com.example.movies.utils.Constants.Companion.MOVIE_ID
import com.example.movies.utils.Constants.Companion.SORT_CRITERIA_TOP
import com.example.movies.utils.Constants.Companion.SORT_CRITERIA_UPCOMING
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_upcoming.*

@AndroidEntryPoint
class UpcomingFragment : Fragment(R.layout.fragment_upcoming) {

    lateinit var adapter: MoviesPagingAdapter
    private val viewModel by viewModels<UpcomingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        adapter.setOnItemClickCallBack(object : MoviesPagingAdapter.OnItemClickCallBack {
            override fun onItemClicked(id: Int?) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(MOVIE_ID, id)
                startActivity(intent)
            }
        })

        viewModel.upcomingMovie(SORT_CRITERIA_UPCOMING)
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    private fun setupRecyclerView() {
        adapter = MoviesPagingAdapter()
        rvUpcoming.adapter = adapter

        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvUpcoming.layoutManager = GridLayoutManager(requireContext(), 3)
            rvUpcoming.run {
                rvUpcoming.addItemDecoration(
                    GridSpacingItemDecoration(
                        3,
                        0,
                        false
                    )
                )
            }
        } else {
            rvUpcoming.layoutManager = GridLayoutManager(requireContext(), 3)
            rvUpcoming.addItemDecoration(
                GridSpacingItemDecoration(
                    3,
                    10,
                    true
                )
            )
        }
    }
}