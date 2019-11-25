package andi.gdk.jetpackpro.data.source

import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.remote.response.TvShowResponse
import andi.gdk.jetpackpro.response.MovieResponse
import androidx.lifecycle.LiveData

interface TheMovieDbDataSource {

    fun getMovies(page: Int): LiveData<ArrayList<MovieEntity>>
    fun getMovie(id: Int?): LiveData<MovieResponse>
    fun getTvShows(page: Int): LiveData<ArrayList<TvShowEntity>>
    fun getTvShow(id: Int?): LiveData<TvShowResponse>

}