package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.data.MovieEntity
import andi.gdk.jetpackpro.utils.generateDummyMovies
import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {

    fun getMovies(): ArrayList<MovieEntity> {
        return generateDummyMovies()
    }

}