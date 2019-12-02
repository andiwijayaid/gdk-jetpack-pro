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

open class LocalRepository private constructor(
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

    open val movies: LiveData<List<MovieEntity>>?
        get() = movieDao?.getMovies()

    open fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>? {
        return movieDao?.getFavoriteMovies()
    }

    open fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>? {
        return tvShowDao?.getFavotiteTvShows()
    }

    open fun getMovieDetail(movieId: Int): LiveData<MovieDetailEntity>? {
        return movieDetailDao?.getMovieDetail(movieId)
    }

    fun insertMovies(movies: List<MovieEntity>) {
        movieDao?.insertMovies(movies)
    }

    fun insertMovieDetail(movieDetail: MovieDetailEntity) {
        movieDetailDao?.insertMovieDetail(movieDetail)
    }

    open val tvShows: LiveData<List<TvShowEntity>>?
        get() = tvShowDao?.getTvShows()

    open fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetailEntity>? {
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

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        tvShowEntity.isFavorite = !tvShowEntity.isFavorite
        tvShowDao?.updateTvShow(tvShowEntity)
    }

    fun setFavoriteTvShowDetail(tvShowDetailEntity: TvShowDetailEntity) {
        tvShowDetailEntity.isFavorite = !tvShowDetailEntity.isFavorite
        tvShowDetailDao?.updateTvShowDetail(tvShowDetailEntity)
    }

}