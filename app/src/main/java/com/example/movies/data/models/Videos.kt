package com.example.movies.data.models


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("results")
    val results: List<VideosResult>
)