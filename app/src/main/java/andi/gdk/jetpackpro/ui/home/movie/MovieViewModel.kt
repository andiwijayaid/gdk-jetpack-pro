package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private var page = 0
    var movies = MutableLiveData<ArrayList<MovieEntity>>()

    fun setPage(page: Int) {
        this.page = page
    }

    fun setMovies() {
        movies = theMovieDbRepository.getMovies(page)
    }

    fun countRetrievedMovies(): Int? {
        return movies.value?.size
    }
}