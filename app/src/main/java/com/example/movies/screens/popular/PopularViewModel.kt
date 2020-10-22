package com.example.movies.screens.popular

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movies.repository.Repository

class PopularViewModel @ViewModelInject constructor(
    repository: Repository
): ViewModel() {
    private val currentSort = MutableLiveData("popular")

    val movies = currentSort.switchMap { sort ->
        repository.getMovie(sort).cachedIn(viewModelScope)
    }

    fun popularMovie(sort: String){
        currentSort.value = sort
    }
}