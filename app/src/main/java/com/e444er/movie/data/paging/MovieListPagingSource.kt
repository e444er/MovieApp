package com.e444er.movie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.e444er.movie.data.mapper.toMovie
import com.e444er.movie.data.remote.api.MovieApi
import com.e444er.movie.domain.model.Movie
import javax.inject.Inject

class MovieListPagingSource(
    private val apiService: MovieApi
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getMovieList(currentPage)
            val data = if (response.results.isNotEmpty()) response.results
                .map { it.toMovie() } else emptyList()
            val responseData = mutableListOf<Movie>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
