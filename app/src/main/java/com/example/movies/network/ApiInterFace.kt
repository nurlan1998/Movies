package com.example.movies.network

import com.example.movies.data.model.DetailMovies
import com.example.movies.data.model.DataMovies
import com.example.movies.utils.Constants.Companion.API_KEY
import retrofit2.Call
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
    fun getDetailMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<DetailMovies>

    @GET("3/search/movie?api_key=7d6c3c25fa66a886b27c1c4437b07c16&language=en-US&page=1&include_adult=false")
    fun getSearchMovies(@Query("query")query: String) : Call<DataMovies>
}