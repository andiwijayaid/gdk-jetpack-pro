package andi.gdk.jetpackpro.data.source

import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.remote.RemoteRepository
import andi.gdk.jetpackpro.data.source.remote.response.TvShowResponse
import andi.gdk.jetpackpro.response.MovieResponse
import androidx.lifecycle.MutableLiveData

open class TheMovieDbRepository private constructor(private val remoteRepository: RemoteRepository) :
    TheMovieDbDataSource {

    companion object {
        @Volatile
        private var INSTANCE: TheMovieDbRepository? = null

        fun getInstance(remoteRepository: RemoteRepository): TheMovieDbRepository? {
            if (INSTANCE == null) {
                synchronized(TheMovieDbRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = TheMovieDbRepository(remoteRepository)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getMovie(id: Int?): MutableLiveData<MovieResponse> {
        val movie = MutableLiveData<MovieResponse>()

        remoteRepository.getMovie(object : RemoteRepository.LoadMovieCallback {
            override fun onMovieRetrieved(movieEntity: MovieResponse?) {
                movie.postValue(movieEntity)
            }

            override fun onFail() {
            }
        }, id)

        return movie
    }

    override fun getMovies(page: Int): MutableLiveData<ArrayList<MovieEntity>> {

        val movies = MutableLiveData<ArrayList<MovieEntity>>()

        remoteRepository.getMovies(object : RemoteRepository.LoadMoviesCallback {
            override fun onMoviesRetrieved(movieEntities: ArrayList<MovieEntity>?) {
                movies.postValue(movieEntities)
            }

            override fun onFail() {
            }
        }, page)

        return movies

    }

    override fun getTvShows(page: Int): MutableLiveData<ArrayList<TvShowEntity>> {

        val tvShows = MutableLiveData<ArrayList<TvShowEntity>>()

        remoteRepository.getTvShows(object : RemoteRepository.LoadTvShowsCallback {
            override fun onTvShowsRetrieved(tvShowsResponse: ArrayList<TvShowEntity>?) {
                tvShows.postValue(tvShowsResponse)
            }

            override fun onFail() {
            }
        }, page)

        return tvShows
    }

    override fun getTvShow(id: Int?): MutableLiveData<TvShowResponse> {
        val tvShow = MutableLiveData<TvShowResponse>()

        remoteRepository.getTvShow(object : RemoteRepository.LoadTvShowCallback {
            override fun onTvShowRetrieved(tvShowEntity: TvShowResponse?) {
                tvShow.postValue(tvShowEntity)
            }

            override fun onFail() {
            }
        }, id)

        return tvShow
    }

}