package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class TvShowViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private var page = 0

    fun setPage(page: Int) {
        this.page = page
    }

    val tvShows: LiveData<ArrayList<TvShowEntity>>
        get() = theMovieDbRepository.getTvShows(page)

}