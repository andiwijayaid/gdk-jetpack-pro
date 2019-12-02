package andi.gdk.jetpackpro.data.source.local

import andi.gdk.jetpackpro.data.source.local.entity.MovieDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.local.room.MovieDao
import andi.gdk.jetpackpro.data.source.local.room.MovieDetailDao
import andi.gdk.jetpackpro.data.source.local.room.TvShowDao
import andi.gdk.jetpackpro.data.source.local.room.TvShowDetailDao
import androidx.lifecycle.LiveData
import androidx.paging.DataSource

class LocalRepository private constructor(
    private val movieDao: MovieDao?,
    private val tvShowDao: TvShowDao?,
    private val movieDetailDao: MovieDetailDao?,
    private val tvShowDetailDao: TvShowDetailDao?
) {

    companion object {

        private var INSTANCE: LocalRepository? = null
        fun getInstance(
            movieDao: MovieDao?,
            tvShowDao: TvShowDao?,
            movieDetailDao: MovieDetailDao?,
            tvShowDetailDao: TvShowDetailDao?
        ): LocalRepository? {
            if (INSTANCE == null) {
                INSTANCE = LocalRepository(movieDao, tvShowDao, movieDetailDao, tvShowDetailDao)
            }
            return INSTANCE
        }

    }

    val movies: LiveData<List<MovieEntity>>?
        get() = movieDao?.getMovies()

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>? {
        return movieDao?.getFavoriteMovies()
    }

    fun getMovieDetail(movieId: Int): LiveData<MovieDetailEntity>? {
        return movieDetailDao?.getMovieDetail(movieId)
    }

    fun insertMovies(movies: List<MovieEntity>) {
        movieDao?.insertMovies(movies)
    }

    fun insertMovieDetail(movieDetail: MovieDetailEntity) {
        movieDetailDao?.insertMovieDetail(movieDetail)
    }

    val tvShows: LiveData<List<TvShowEntity>>?
        get() = tvShowDao?.getTvShows()

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetailEntity>? {
        return tvShowDetailDao?.getTvShowDetail(tvShowId)
    }

    fun insertTvShows(tvShows: List<TvShowEntity>) {
        tvShowDao?.insertTvShows(tvShows)
    }

    fun insertTvShowDetail(tvShowDetail: TvShowDetailEntity) {
        tvShowDetailDao?.insertTvShowDetail(tvShowDetail)
    }

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        movieEntity.isFavorite = !movieEntity.isFavorite
        movieDao?.updateMovie(movieEntity)
    }

    fun setFavoriteMovieDetail(movieDetailEntity: MovieDetailEntity) {
        movieDetailEntity.isFavorite = !movieDetailEntity.isFavorite
        movieDetailDao?.updateMovieDetail(movieDetailEntity)
    }

}