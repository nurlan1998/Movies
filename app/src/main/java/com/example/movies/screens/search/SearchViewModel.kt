package com.example.movies.screens.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.models.DataMovies
import com.example.movies.repository.Repository
import com.example.movies.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val searchLiveData: MutableLiveData<Resource<DataMovies>> = MutableLiveData()

    fun getSearchMovie(query: String) = viewModelScope.launch {
        val search = repository.getSearchMovie(query)
        searchLiveData.postValue(handleSearchMovieResponse(search))
    }

    private fun handleSearchMovieResponse(responce: Response<DataMovies>): Resource<DataMovies> {
        if (responce.isSuccessful) {
            responce.body()?.let { resultResponce ->
                return Resource.Success(resultResponce)
            }
        }
        return Resource.Error(responce.message())
    }
}