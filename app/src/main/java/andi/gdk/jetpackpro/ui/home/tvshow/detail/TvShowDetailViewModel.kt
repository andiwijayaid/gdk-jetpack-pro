package andi.gdk.jetpackpro.ui.home.tvshow.detail

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.remote.response.TvShowResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TvShowDetailViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private var id = 0
    var tvShow = MutableLiveData<TvShowResponse>()

    fun setId(id: Int?) {
        if (id != null) {
            this.id = id
        }
    }

    fun setTvShow() {
        tvShow = theMovieDbRepository.getTvShow(id)
    }

}