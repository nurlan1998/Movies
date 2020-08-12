package com.example.movies.network

import com.example.movies.data.model.MovieVoteAverage
import com.example.movies.data.model.PopularMovies
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterFace {
//    @GET("3/discover/movie?api_key=7d6c3c25fa66a886b27c1c4437b07c16&language=ru-RU&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
//    suspend fun getMovies() : Response<MovieVoteAverage>
    @GET("3/discover/movie?api_key=7d6c3c25fa66a886b27c1c4437b07c16&language=ru-RU&sort_by=revenue.desc&include_adult=false&include_video=false&page=1")
    suspend fun getMovies() : Response<MovieVoteAverage>
}