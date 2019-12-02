package andi.gdk.jetpackpro.ui.home.favorite.tvshow

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

class FavoriteTvShowViewModel(private val theMovieDbRepository: TheMovieDbRepository) :
    ViewModel() {

    val favoriteTvShows: LiveData<Resource<PagedList<TvShowEntity>>>
        get() = theMovieDbRepository.getFavoriteTvShows()

}