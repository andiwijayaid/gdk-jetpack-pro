package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TvShowViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private var page = 0
    var tvShows = MutableLiveData<ArrayList<TvShowEntity>>()

    fun setPage(page: Int) {
        this.page = page
    }


    fun setTvShows() {
        tvShows = theMovieDbRepository.getTvShows(page)
    }

    fun countRetrievedTvShows(): Int? {
        return tvShows.value?.size
    }

}