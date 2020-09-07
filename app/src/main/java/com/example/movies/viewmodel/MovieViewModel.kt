package com.example.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.model.DataMovies
import com.example.movies.data.model.DetailMovies
import com.example.movies.repository.DetailRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(private val repository: DetailRepository) : ViewModel() {
    val movieLiveData: MutableLiveData<Response<DetailMovies>> = MutableLiveData()
    val searchLiveData: MutableLiveData<Response<DataMovies>> = MutableLiveData()

    fun getDetailMovie(id: Int) {
        viewModelScope.launch {
            val responce = repository.getDetailMovie(id)
            movieLiveData.value = responce
        }
    }

    fun getSearchMovie(query: String) {
        viewModelScope.launch {
            val search = repository.getSearchMovie(query)
            searchLiveData.value = search
        }
    }
}