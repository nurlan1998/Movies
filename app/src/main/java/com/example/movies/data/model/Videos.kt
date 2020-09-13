package com.example.movies.data.model


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("results")
    val results: List<VideosResult>
)