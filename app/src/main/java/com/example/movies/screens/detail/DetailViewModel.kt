package com.example.movies.screens.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.models.DetailMovies
import com.example.movies.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    val movieLiveData: MutableLiveData<Response<DetailMovies>> = MutableLiveData()

    fun getDetailMovie(id: Int) = viewModelScope.launch {
        val responce = repository.getDetailMovie(id)
        movieLiveData.value = responce
    }
}