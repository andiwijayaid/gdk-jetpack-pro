package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class TvShowViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private val mLogin = MutableLiveData<String>()

    fun setUsername(username: String) {
        mLogin.value = username
    }

    fun countRetrievedTvShows(): Int? {
        return tvShows.value?.data?.size
    }

    var tvShows: LiveData<Resource<List<TvShowEntity>>> = Transformations.switchMap(mLogin) {
        theMovieDbRepository.getTvShows()
    }

}