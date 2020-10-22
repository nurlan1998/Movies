package com.example.movies.repository

import androidx.paging.PagingSource
import com.example.movies.data.models.DataMoviesResult
import com.example.movies.data.network.ApiInterFace
import com.example.movies.utils.Constants.Companion.API_KEY
import com.example.movies.utils.Constants.Companion.LANGUAGE
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class MoviePagingSource(
    private val apiInterFace: ApiInterFace,
    private val sort: String
) : PagingSource<Int, DataMoviesResult>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataMoviesResult> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {

            val response =
                apiInterFace.getMovies(sort, API_KEY, LANGUAGE, position, params.loadSize)
            val movies = response.body()!!.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

}