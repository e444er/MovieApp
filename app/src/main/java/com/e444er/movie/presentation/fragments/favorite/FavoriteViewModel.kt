package com.e444er.movie.presentation.fragments.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e444er.movie.domain.model.Movie
import com.e444er.movie.domain.usecase.GetMovieAddUseCase
import com.e444er.movie.domain.usecase.GetMovieDeleteUseCase
import com.e444er.movie.domain.usecase.GetMovieIdUseCase
import com.e444er.movie.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getMovieIdUseCase: GetMovieIdUseCase,
    private val getMovieDeleteUseCase: GetMovieDeleteUseCase,
    private val getMovieAddUseCase: GetMovieAddUseCase,
) :ViewModel(){

    private val _favoriteMovies = MutableStateFlow(emptyList<Movie>())
    val favoriteMovies get()  = _favoriteMovies.asStateFlow()

    private val _saveMovies = MutableLiveData<Boolean>(false)
    val saveMovies: LiveData<Boolean>  = _saveMovies


    fun isClick(click: Boolean){
        _saveMovies.value = click
    }


    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieUseCase().collect {
                _favoriteMovies.value = it as List<Movie>
            }
        }
    }


     fun getMoviesId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieIdUseCase(id)
        }
    }

    fun deleteMovies(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieDeleteUseCase(movie)
            getMovies()
        }
    }



    fun addMovies(movie:Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieAddUseCase(movie)
            getMovies()
        }
    }

}