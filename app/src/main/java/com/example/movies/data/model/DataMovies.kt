package com.example.movies.data.model


import com.google.gson.annotations.SerializedName

data class DataMovies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<DataMoviesResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)