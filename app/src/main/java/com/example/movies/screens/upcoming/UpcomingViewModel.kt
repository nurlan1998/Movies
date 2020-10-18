package com.example.movies.screens.upcoming

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movies.repository.Repository

class UpcomingViewModel @ViewModelInject constructor(
private  val repository: Repository
): ViewModel() {
    private val currentSort = MutableLiveData("upcoming")

    val movies = currentSort.switchMap { sort ->
        repository.getMovie(sort).cachedIn(viewModelScope)
    }

    fun upcomingMovie(sort: String){
        currentSort.value = sort
    }
}