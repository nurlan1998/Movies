package com.example.movies.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movies.data.model.DataMoviesResult

class MovieDataSourceFactory(var sort_criteria: String) :
    DataSource.Factory<Int, DataMoviesResult>() {

    val movieLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, DataMoviesResult> {
        val movieDataSource = MovieDataSource(sort_criteria)
        movieLiveDataSource.postValue(movieDataSource)

        return movieDataSource
    }
}