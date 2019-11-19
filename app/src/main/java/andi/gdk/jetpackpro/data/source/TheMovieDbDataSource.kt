package andi.gdk.jetpackpro.data.source

import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import androidx.lifecycle.LiveData

interface TheMovieDbDataSource {

    fun getMovies(page: Int): LiveData<ArrayList<MovieEntity>>
    fun getMovie(id: Int): LiveData<MovieEntity>
    fun getTvShows(page: Int): LiveData<ArrayList<TvShowEntity>>
    fun getTvShow(id: Int): LiveData<TvShowEntity>

}