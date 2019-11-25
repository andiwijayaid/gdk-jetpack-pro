package andi.gdk.jetpackpro.data.source

import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.remote.RemoteRepository
import andi.gdk.jetpackpro.data.source.remote.response.TvShowResponse
import andi.gdk.jetpackpro.response.MovieResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeTheMovieDbRepository(private val remoteRepository: RemoteRepository) :
    TheMovieDbDataSource {

    override fun getMovie(id: Int?): LiveData<MovieResponse> {
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
            }
        }, page)

        return tvShows
    }

    override fun getTvShow(id: Int?): LiveData<TvShowResponse> {
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