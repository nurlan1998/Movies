package com.example.movies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.DetailMovies
import com.example.movies.network.ApiInterFace
import com.example.movies.network.RetrofitInstance
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    lateinit var retrofitService: ApiInterFace

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var id = intent.getIntExtra("id",0)

        retrofitService = RetrofitInstance.getRetrofitInstance().create(ApiInterFace::class.java)

        retrofitService.getDetailMovies(id).enqueue(object : Callback<DetailMovies> {
            override fun onFailure(call: Call<DetailMovies>, t: Throwable) {
                Log.e("Repository", "onFailure", t)
            }

            override fun onResponse(call: Call<DetailMovies>, response: Response<DetailMovies>) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        Log.i("detailName",responseBody.title)
                        showDetail(responseBody)
                    }else
                        Log.d("Repository","Failed to get repository")
                }
            }

        })
    }
    fun showDetail(detail: DetailMovies){
        tvDetailTitle.text = detail.title
        tvReleaseDate.text = detail.releaseDate
        tvDetailOverView.text = detail.overview

        Glide.with(this).load("https://image.tmdb.org/t/p/original${detail.posterPath}")
            .into(ivDetailMovie)
    }
}