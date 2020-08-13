package com.example.movies.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movies.data.model.MoviesVoteResult

class MovieDataSourceFactory: DataSource.Factory<Int,MoviesVoteResult>() {

    val movieLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MoviesVoteResult> {
        val movieDataSource = MovieDataSource()
        movieLiveDataSource.postValue(movieDataSource)

        return movieDataSource
    }
}