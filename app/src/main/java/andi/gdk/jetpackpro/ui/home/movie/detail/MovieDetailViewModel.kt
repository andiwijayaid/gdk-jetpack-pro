package andi.gdk.jetpackpro.ui.home.movie.detail

import andi.gdk.jetpackpro.data.MovieEntity
import andi.gdk.jetpackpro.utils.generateDummyMovies
import androidx.lifecycle.ViewModel

class MovieDetailViewModel : ViewModel() {

    private lateinit var movieEntity: MovieEntity
    private var movieTitle: String? = null

    fun getMovie(): MovieEntity {
        for (movie in generateDummyMovies()) {
            if (movie.title == movieTitle) {
                movieEntity = movie
            }
        }
        return movieEntity
    }

    fun setMovieTitle(movieTitle: String?) {
        this.movieTitle = movieTitle
    }
}