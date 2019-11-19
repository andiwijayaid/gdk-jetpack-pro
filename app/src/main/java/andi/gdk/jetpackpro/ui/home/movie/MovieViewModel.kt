package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MovieViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private var page = 0

    fun setPage(page: Int) {
        this.page = page
    }

    val movies: LiveData<ArrayList<MovieEntity>>
        get() = theMovieDbRepository.getMovies(page)

}