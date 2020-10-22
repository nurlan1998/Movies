package com.example.movies.data.network

import com.example.movies.data.models.DetailMovies
import com.example.movies.data.models.DataMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterFace {

    @GET("3/movie/{sort_criteria}")
    suspend fun getMovies(
        @Path("sort_criteria") sort_criteria: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<DataMovies>

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