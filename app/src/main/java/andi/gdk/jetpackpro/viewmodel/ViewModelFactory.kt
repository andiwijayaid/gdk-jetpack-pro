package andi.gdk.jetpackpro.viewmodel

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.di.Injection
import andi.gdk.jetpackpro.ui.home.movie.MovieViewModel
import andi.gdk.jetpackpro.ui.home.movie.detail.MovieDetailViewModel
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowViewModel
import andi.gdk.jetpackpro.ui.home.tvshow.detail.TvShowDetailViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val theMovieDbRepository: TheMovieDbRepository) :
    ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(theMovieDbRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(
                theMovieDbRepository
            ) as T
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> MovieDetailViewModel(
                theMovieDbRepository
            ) as T
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> TvShowDetailViewModel(
                theMovieDbRepository
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Injection.provideRepository()?.let { ViewModelFactory(it) }
                    }
                }
            }
            return INSTANCE
        }
    }
}
