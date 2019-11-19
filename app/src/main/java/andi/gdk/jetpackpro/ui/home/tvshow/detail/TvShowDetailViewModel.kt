package andi.gdk.jetpackpro.ui.home.tvshow.detail

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class TvShowDetailViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private var id = 0

    fun setId(id: Int) {
        this.id = id
    }

    val tvShow: LiveData<TvShowEntity>
        get() = theMovieDbRepository.getTvShow(id)

}