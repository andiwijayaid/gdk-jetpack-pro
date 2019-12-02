package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MovieViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private val mLogin = MutableLiveData<String>()

    fun setUsername(username: String) {
        mLogin.value = username
    }

    fun countRetrievedMovies(): Int? {
        return movies.value?.data?.size
    }

    var movies: LiveData<Resource<List<MovieEntity>>> = Transformations.switchMap(mLogin) {
        theMovieDbRepository.getMovies()
    }
}