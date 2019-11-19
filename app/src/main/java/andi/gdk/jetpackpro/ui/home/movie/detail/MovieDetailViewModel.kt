package andi.gdk.jetpackpro.ui.home.movie.detail

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MovieDetailViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private var id = 0

    fun setId(id: Int) {
        this.id = id
    }

    val movie: LiveData<MovieEntity>
        get() = theMovieDbRepository.getMovie(id)

}