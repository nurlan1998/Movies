package com.example.movies.repository

import com.example.movies.data.model.DataMovies
import com.example.movies.data.model.DetailMovies
import com.example.movies.network.RetrofitInstance
import com.example.movies.utils.Constants.Companion.API_KEY
import com.example.movies.utils.Constants.Companion.LANGUAGE
import com.example.movies.utils.Constants.Companion.VIDEOS
import retrofit2.Response

class DetailRepository {
    suspend fun getDetailMovie(movieId: Int): Response<DetailMovies> {
        return RetrofitInstance.api.getDetailMovies(movieId, API_KEY, LANGUAGE, VIDEOS)
    }

    suspend fun getSearchMovie(query: String): Response<DataMovies> {
        return RetrofitInstance.api.getSearchMovies(query, API_KEY, LANGUAGE)
    }
}