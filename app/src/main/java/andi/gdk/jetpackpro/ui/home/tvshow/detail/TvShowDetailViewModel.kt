package andi.gdk.jetpackpro.ui.home.tvshow.detail

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.TvShowDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class TvShowDetailViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private var id = MutableLiveData<Int>()

    var tvShow: LiveData<Resource<TvShowDetailEntity>> = Transformations.switchMap(id) {
        theMovieDbRepository.getTvShow(it)
    }


    fun setId(id: Int?) {
        if (id != null) {
            this.id.value = id
        }
    }

    fun setFavorite(tvShowEntity: TvShowEntity?) {
        if (tvShow.value != null) {
            val movie = tvShow.value!!.data
            if (movie != null && tvShowEntity != null) {
                theMovieDbRepository.setFavoriteTvShow(tvShowEntity)
                theMovieDbRepository.setFavoriteTvShowDetail(movie)
            }
        }
    }
}