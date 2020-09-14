package com.example.movies.network

import com.example.movies.data.model.DetailMovies
import com.example.movies.data.model.DataMovies
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterFace {
    //    @GET("3/discover/movie?api_key=7d6c3c25fa66a886b27c1c4437b07c16&language=ru-RU&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
//    suspend fun getMovies() : Response<MovieVoteAverage>
    @GET("3/movie/{sort_criteria}")
    fun getMovies(
        @Path("sort_criteria") sort_criteria: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<DataMovies>

    @GET("3/movie/{movie_id}")
    suspend fun getDetailMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") append_to_response: String
    ): Response<DetailMovies>

    @GET("3/search/movie")
    suspend fun getSearchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<DataMovies>
}