package andi.gdk.jetpackpro.ui.home.movie.detail

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.response.MovieResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieDetailViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private var id = 0

    var movie = MutableLiveData<MovieResponse>()

    fun setId(id: Int?) {
        if (id != null) {
            this.id = id
        }
    }

    fun setMovie() {
//        movie = theMovieDbRepository.getMovie(id)
    }

}