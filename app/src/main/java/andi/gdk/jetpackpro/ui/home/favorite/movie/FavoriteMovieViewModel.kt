package andi.gdk.jetpackpro.ui.home.favorite.movie

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

class FavoriteMovieViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    val favoriteMovies: LiveData<Resource<PagedList<MovieEntity>>>
        get() = theMovieDbRepository.getFavoriteMovies()

}