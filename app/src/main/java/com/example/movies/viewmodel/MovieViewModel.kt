package com.example.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.model.DataMovies
import com.example.movies.data.model.DetailMovies
import com.example.movies.repository.DetailRepository
import com.example.movies.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(private val repository: DetailRepository) : ViewModel() {
    val movieLiveData: MutableLiveData<Response<DetailMovies>> = MutableLiveData()
    val searchLiveData: MutableLiveData<Resource<DataMovies>> = MutableLiveData()

    fun getDetailMovie(id: Int) = viewModelScope.launch {
            val responce = repository.getDetailMovie(id)
            movieLiveData.value = responce
    }

    fun getSearchMovie(query: String) = viewModelScope.launch {
            val search = repository.getSearchMovie(query)
            searchLiveData.postValue(handleSearchMovieResponse(search))
    }

    private fun handleSearchMovieResponse(responce: Response<DataMovies>): Resource<DataMovies>{
        if(responce.isSuccessful){
            responce.body()?.let {resultResponce ->
                return Resource.Success(resultResponce)
            }
        }
        return Resource.Error(responce.message())
    }
}