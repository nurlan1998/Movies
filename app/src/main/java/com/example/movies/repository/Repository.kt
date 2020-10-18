package com.example.movies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.movies.data.models.DataMovies
import com.example.movies.data.models.DetailMovies
import com.example.movies.data.network.ApiInterFace
import com.example.movies.utils.Constants
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val apiInterFace: ApiInterFace) {

    fun getMovie(sort: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(
                    apiInterFace,
                    sort
                )
            }
        ).liveData

    suspend fun getDetailMovie(movieId: Int): Response<DetailMovies> {
        return apiInterFace.getDetailMovies(
            movieId,
            Constants.API_KEY,
            Constants.LANGUAGE,
            Constants.VIDEOS
        )
    }

    suspend fun getSearchMovie(query: String): Response<DataMovies> {
        return apiInterFace.getSearchMovies(query, Constants.API_KEY, Constants.LANGUAGE)
    }
}