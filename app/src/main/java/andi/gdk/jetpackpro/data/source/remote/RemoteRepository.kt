package andi.gdk.jetpackpro.data.source.remote

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.api.ApiConfig
import andi.gdk.jetpackpro.data.source.MovieData
import andi.gdk.jetpackpro.data.source.TvShowData
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.remote.response.MoviesResponse
import andi.gdk.jetpackpro.data.source.remote.response.TvShowsResponse
import andi.gdk.jetpackpro.utils.EspressoIdlingResource
import android.util.Log
import retrofit2.Call
import retrofit2.Response

class RemoteRepository {

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(): RemoteRepository {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository()
            }
            return INSTANCE as RemoteRepository
        }
    }

    fun getMovies(loadMoviesCallback: LoadMoviesCallback, page: Int) {
        EspressoIdlingResource.increment()
        ApiConfig().instance().getMovies(
            BuildConfig.TOKEN, page
        ).enqueue(object : retrofit2.Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.d("M Fail", t.message.toString())
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                EspressoIdlingResource.decrement()
                loadMoviesCallback.onMoviesRetrieved(response.body()?.movies)
            }
        })
    }

    interface LoadMoviesCallback {
        fun onMoviesRetrieved(movieEntities: ArrayList<MovieEntity>?)
        fun onFail()
    }

    fun getMovie(loadMoviesCallback: LoadMovieCallback, id: Int) {
        EspressoIdlingResource.increment()
        val movieData = MovieData()
        loadMoviesCallback.onMovieRetrieved(movieData.loadMovie(id))
        EspressoIdlingResource.decrement()
    }

    interface LoadMovieCallback {
        fun onMovieRetrieved(movieEntity: MovieEntity?)
        fun onFail()
    }

    fun getTvShows(loadTvShowsCallback: LoadTvShowsCallback, page: Int) {
        EspressoIdlingResource.increment()
        ApiConfig().instance().getTvShows(
            BuildConfig.TOKEN, page
        ).enqueue(object : retrofit2.Callback<TvShowsResponse> {
            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.d("M Fail", t.message.toString())
            }

            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                EspressoIdlingResource.decrement()
                loadTvShowsCallback.onTvShowsRetrieved(response.body()?.tvShows)
            }
        })
    }

    interface LoadTvShowsCallback {
        fun onTvShowsRetrieved(tvShowsResponse: ArrayList<TvShowEntity>?)
        fun onFail()
    }

    fun getTvShow(loadTvShowCallback: LoadTvShowCallback, id: Int) {
        EspressoIdlingResource.increment()
        val tvShowData = TvShowData()
        loadTvShowCallback.onTvShowRetrieved(tvShowData.loadTvShow(id))
        EspressoIdlingResource.decrement()
    }

    interface LoadTvShowCallback {
        fun onTvShowRetrieved(tvShowEntity: TvShowEntity?)
        fun onFail()
    }

}