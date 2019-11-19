package andi.gdk.jetpackpro.data.source

import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.remote.RemoteRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TheMovieDbRepository private constructor(private val remoteRepository: RemoteRepository) :
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

    override fun getMovie(id: Int): LiveData<MovieEntity> {
        val movie = MutableLiveData<MovieEntity>()

        remoteRepository.getMovie(object : RemoteRepository.LoadMovieCallback {
            override fun onMovieRetrieved(movieEntity: MovieEntity?) {
                movie.postValue(movieEntity)
            }

            override fun onFail() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }, id)

        return movie
    }

    override fun getMovies(page: Int): LiveData<ArrayList<MovieEntity>> {

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

    override fun getTvShows(page: Int): LiveData<ArrayList<TvShowEntity>> {

        val tvShows = MutableLiveData<ArrayList<TvShowEntity>>()

        remoteRepository.getTvShows(object : RemoteRepository.LoadTvShowsCallback {
            override fun onTvShowsRetrieved(tvShowsResponse: ArrayList<TvShowEntity>?) {
                tvShows.postValue(tvShowsResponse)
            }

            override fun onFail() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }, page)

        return tvShows
    }

    override fun getTvShow(id: Int): LiveData<TvShowEntity> {
        val tvShow = MutableLiveData<TvShowEntity>()

        remoteRepository.getTvShow(object : RemoteRepository.LoadTvShowCallback {
            override fun onTvShowRetrieved(tvShowEntity: TvShowEntity?) {
                tvShow.postValue(tvShowEntity)
            }

            override fun onFail() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }, id)

        return tvShow
    }

}