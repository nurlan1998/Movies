package com.example.movies.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.movies.data.model.DataMovies
import com.example.movies.data.model.DataMoviesResult
import com.example.movies.network.RetrofitInstance
import com.example.movies.utils.Constants.Companion.API_KEY
import com.example.movies.utils.Constants.Companion.LANGUAGE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource(var sort_criteria: String) : PageKeyedDataSource<Int, DataMoviesResult>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataMoviesResult>
    ) {

        RetrofitInstance.api.getMovies(sort_criteria, API_KEY, LANGUAGE, FIRST_PAGE)
            .enqueue(object : Callback<DataMovies> {
                override fun onFailure(call: Call<DataMovies>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }

                override fun onResponse(call: Call<DataMovies>, response: Response<DataMovies>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            val responseItems = apiResponse.results
                            responseItems.let {
                                callback.onResult(responseItems, null, FIRST_PAGE + 1)
                            }
                        } else
                            Log.d("Repository", "Failed to get repository")
                    }
                }

            })
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataMoviesResult>) {

        RetrofitInstance.api.getMovies(sort_criteria, API_KEY, LANGUAGE, params.key)
            .enqueue(object : Callback<DataMovies> {
                override fun onFailure(call: Call<DataMovies>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }

                override fun onResponse(call: Call<DataMovies>, response: Response<DataMovies>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            val responseItems = apiResponse.results

                            val key = params.key + 1

                            responseItems.let {
                                callback.onResult(responseItems, key)
                            }
                        } else
                            Log.d("Repository", "Failed to get repository")
                    }
                }

            })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, DataMoviesResult>
    ) {


        RetrofitInstance.api.getMovies(sort_criteria, API_KEY, LANGUAGE, FIRST_PAGE)
            .enqueue(object : Callback<DataMovies> {
                override fun onFailure(call: Call<DataMovies>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }

                override fun onResponse(call: Call<DataMovies>, response: Response<DataMovies>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            val responseItems = apiResponse.results

                            val key = if (params.key > 1) params.key - 1 else 0

                            responseItems.let {
                                callback.onResult(responseItems, key)
                            }
                        } else
                            Log.d("Repository", "Failed to get repository")
                    }
                }

            })

    }

    companion object {
        const val PAGE_SIZE = 20
        const val FIRST_PAGE = 1
    }


}