package com.example.movies.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.adapter.GridSpacingItemDecoration
import com.example.movies.adapter.MoviesAdapter
import com.example.movies.utils.Constants.Companion.MOVIE_ID
import com.example.movies.utils.Constants.Companion.SORT_CRITERIA_POP
import com.example.movies.viewmodel.PagingViewModel
import com.example.movies.viewmodel.PagingViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment(R.layout.fragment_popular) {

    lateinit var adapter: MoviesAdapter
    private var sort_criteria = SORT_CRITERIA_POP

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        adapter.setOnItemClickCallBack(object : MoviesAdapter.OnItemClickCallBack {
            override fun onItemClicked(id: Int?) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(MOVIE_ID, id)
                startActivity(intent)
            }
        })

        val itemViewModel = ViewModelProviders.of(this, PagingViewModelFactory(sort_criteria))
            .get(PagingViewModel::class.java)
        itemViewModel.moviePagedList.observe(requireActivity(), Observer {
            adapter.submitList(it)
        })
    }

    private fun setupRecyclerView(){
        adapter = MoviesAdapter()
        rvPopular.adapter = adapter

        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvPopular.layoutManager = GridLayoutManager(requireContext(), 3)
            rvPopular.addItemDecoration(GridSpacingItemDecoration(3, 0, false))
        } else {
            rvPopular.layoutManager = GridLayoutManager(requireContext(), 3)
            rvPopular.addItemDecoration(GridSpacingItemDecoration(3, 10, false))
        }
    }
}