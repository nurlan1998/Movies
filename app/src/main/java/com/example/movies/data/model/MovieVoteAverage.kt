package com.example.movies.data.model


import com.google.gson.annotations.SerializedName

data class MovieVoteAverage(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MoviesVoteResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)