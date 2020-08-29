package com.example.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movies.data.model.DataMoviesResult
import com.example.movies.datasource.MovieDataSource
import com.example.movies.datasource.MovieDataSource.Companion.PAGE_SIZE
import com.example.movies.datasource.MovieDataSourceFactory

class MovieViewModel(var sort_criteria: String) : ViewModel() {

    val moviePagedList: LiveData<PagedList<DataMoviesResult>>

    private val liveDataSource: LiveData<MovieDataSource>

    init {
        val itemDataSourceFactory = MovieDataSourceFactory(sort_criteria)

        liveDataSource = itemDataSourceFactory.movieLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()

        moviePagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()

    }
}