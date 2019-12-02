package andi.gdk.jetpackpro.ui.home.movie.detail

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieDetailEntity
import andi.gdk.jetpackpro.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MovieDetailViewModel(private val theMovieDbRepository: TheMovieDbRepository) : ViewModel() {

    private var id = MutableLiveData<Int>()

    var movie: LiveData<Resource<MovieDetailEntity>> = Transformations.switchMap(id) {
        theMovieDbRepository.getMovie(it)
    }

    fun setId(id: Int?) {
        if (id != null) {
            this.id.value = id
        }
    }

}