package com.fadhil.challenge.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.local.entity.Movie
import com.fadhil.challenge.data.source.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject
constructor(private val movieRepository: MovieRepository) : ViewModel() {

    companion object {
        var page: Int = 1
    }

    private lateinit var movies: LiveData<Result<List<Movie>>>

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getMovies(): LiveData<Result<List<Movie>>> {
        movies = fetchMovies()
        return movies
    }

    private fun fetchMovies(): LiveData<Result<List<Movie>>> {
        // If any transformation is needed, this can be simply done by Transformations class ...
        return movieRepository.getMovies(page)
    }
}