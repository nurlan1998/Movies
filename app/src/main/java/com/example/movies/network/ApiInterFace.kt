package com.example.movies.network

import com.example.movies.data.model.DataMovies
import com.example.movies.data.model.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterFace {
    @GET("3/discover/movie?api_key=7d6c3c25fa66a886b27c1c4437b07c16&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getMovies() : Response<DataMovies>
}