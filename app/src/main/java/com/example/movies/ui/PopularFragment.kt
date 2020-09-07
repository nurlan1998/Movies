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
import com.example.movies.viewmodel.PagingViewModel
import com.example.movies.viewmodel.PagingViewModelFactory
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
        val itemViewModel = ViewModelProviders.of(this, PagingViewModelFactory(sort_criteria))
            .get(PagingViewModel::class.java)
        itemViewModel.moviePagedList.observe(requireActivity(), Observer {
            adapter.submitList(it)
        })
    }
}