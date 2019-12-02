package andi.gdk.jetpackpro.data.source

import andi.gdk.jetpackpro.data.source.local.entity.MovieDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.vo.Resource
import androidx.lifecycle.LiveData
import androidx.paging.PagedList

interface TheMovieDbDataSource {

    fun getMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getMovie(id: Int): LiveData<Resource<MovieDetailEntity>>
    fun getTvShows(): LiveData<Resource<List<TvShowEntity>>>
    fun getTvShow(id: Int): LiveData<Resource<TvShowDetailEntity>>

    fun getFavoriteMovies(): LiveData<Resource<PagedList<MovieEntity>>>

}