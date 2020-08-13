package com.example.movies.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.movies.data.model.MovieVoteAverage
import com.example.movies.data.model.MoviesVoteResult
import com.example.movies.network.ApiInterFace
import com.example.movies.network.RetrofitInstance
import com.example.movies.ui.home.HomeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource : PageKeyedDataSource<Int,MoviesVoteResult>() {

    lateinit var retrofitService: ApiInterFace
    private val adapter = HomeAdapter()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MoviesVoteResult>
    ) {

        retrofitService = RetrofitInstance.getRetrofitInstance().create(ApiInterFace::class.java)

        retrofitService.getMovies(FIRST_PAGE).enqueue(object : Callback<MovieVoteAverage> {
            override fun onFailure(call: Call<MovieVoteAverage>, t: Throwable) {
                Log.e("Repository", "onFailure", t)
            }

            override fun onResponse(call: Call<MovieVoteAverage>, response: Response<MovieVoteAverage>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()
                    if(apiResponse != null){
//                        adapter.models = responseBody.results
                        val responseItems = apiResponse.results
                        responseItems.let {
                            callback.onResult(responseItems,null, FIRST_PAGE+1)
                        }
                    }else
                        Log.d("Repository","Failed to get repository")
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MoviesVoteResult>) {

        retrofitService.getMovies(params.key).enqueue(object : Callback<MovieVoteAverage> {
            override fun onFailure(call: Call<MovieVoteAverage>, t: Throwable) {
                Log.e("Repository", "onFailure", t)
            }

            override fun onResponse(call: Call<MovieVoteAverage>, response: Response<MovieVoteAverage>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()
                    if(apiResponse != null){
//                        adapter.models = responseBody.results
                        val responseItems = apiResponse.results

                        val key = params.key + 1

                        responseItems.let {
                            callback.onResult(responseItems,key)
                        }
                    }else
                        Log.d("Repository","Failed to get repository")
                }
            }

        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MoviesVoteResult>) {


        retrofitService.getMovies(FIRST_PAGE).enqueue(object : Callback<MovieVoteAverage> {
            override fun onFailure(call: Call<MovieVoteAverage>, t: Throwable) {
                Log.e("Repository", "onFailure", t)
            }

            override fun onResponse(call: Call<MovieVoteAverage>, response: Response<MovieVoteAverage>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()
                    if(apiResponse != null){
//                        adapter.models = responseBody.results
                        val responseItems = apiResponse.results

                        val key = if(params.key > 1)params.key - 1 else 0

                        responseItems.let {
                            callback.onResult(responseItems,key)
                        }
                    }else
                        Log.d("Repository","Failed to get repository")
                }
            }

        })

    }
    companion object{
        const val PAGE_SIZE = 20
        const val FIRST_PAGE = 1
    }

}