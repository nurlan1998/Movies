package com.example.movies.network

import com.example.movies.data.model.DetailMovies
import com.example.movies.data.model.MovieVoteAverage
import com.example.movies.data.model.PopularMovies
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterFace {
//    @GET("3/discover/movie?api_key=7d6c3c25fa66a886b27c1c4437b07c16&language=ru-RU&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
//    suspend fun getMovies() : Response<MovieVoteAverage>
    @GET("3/discover/movie?api_key=7d6c3c25fa66a886b27c1c4437b07c16&language=ru-RU&sort_by=revenue.desc&include_adult=false&include_video=false")
    fun getMovies(@Query("page") page: Int) : Call<MovieVoteAverage>

    @GET("3/movie/{movie_id}?api_key=7d6c3c25fa66a886b27c1c4437b07c16&language=ru-RU")
    fun getDetailMovies(@Path("movie_id") id: Int) : Call<DetailMovies>

    @GET("3/movie/popular?api_key=7d6c3c25fa66a886b27c1c4437b07c16&language=ru-RU&page=1")
    fun getPopularMovies() : Call<MovieVoteAverage>
}