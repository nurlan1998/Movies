package com.example.movies.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.adapter.GridSpacingItemDecoration
import com.example.movies.adapter.SearchAdapter
import com.example.movies.repository.DetailRepository
import com.example.movies.utils.Constants.Companion.MOVIE_ID
import com.example.movies.viewmodel.MovieViewModel
import com.example.movies.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(R.layout.fragment_search) {

    var searchAdapter = SearchAdapter()
    private lateinit var viewModel: MovieViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSearch.adapter = searchAdapter

        if(activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvSearch.layoutManager = GridLayoutManager(requireContext(),3)
            rvSearch.addItemDecoration(GridSpacingItemDecoration(3,0,false))
        } else {
            rvSearch.layoutManager = GridLayoutManager(requireContext(),3)
            rvSearch.addItemDecoration(GridSpacingItemDecoration(3,10,true))
        }

        searchAdapter.setOnItemClickListener {
            var intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(MOVIE_ID, it)
            startActivity(intent)
        }

        val repository = DetailRepository()
        val viewModelFactory = MovieViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        etSearch.addTextChangedListener {
            viewModel.getSearchMovie(it.toString())
        }

        viewModel.searchLiveData.observe(requireActivity(), Observer {
            Log.i("Search", it.toString())
            val responceBody = it.body()?.results
            if (responceBody != null) searchAdapter.models = responceBody
        })
    }
}