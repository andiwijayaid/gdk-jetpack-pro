package andi.gdk.jetpackpro.data.source.remote

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.api.ApiConfig
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.remote.response.MoviesResponse
import andi.gdk.jetpackpro.data.source.remote.response.TvShowResponse
import andi.gdk.jetpackpro.data.source.remote.response.TvShowsResponse
import andi.gdk.jetpackpro.response.MovieResponse
import andi.gdk.jetpackpro.utils.EspressoIdlingResource
import android.util.Log
import retrofit2.Call
import retrofit2.Response

open class RemoteRepository {

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
                loadMoviesCallback.onMoviesRetrieved(response.body()?.movies)
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadMoviesCallback {
        fun onMoviesRetrieved(movieEntities: ArrayList<MovieEntity>?)
        fun onFail()
    }

    fun getMovie(loadMoviesCallback: LoadMovieCallback, id: Int) {
        EspressoIdlingResource.increment()
        ApiConfig().instance().getMovie(id, BuildConfig.TOKEN)
            .enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("M Fail", t.message.toString())
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    EspressoIdlingResource.decrement()
                    loadMoviesCallback.onMovieRetrieved(response.body())
                }
            })
    }

    interface LoadMovieCallback {
        fun onMovieRetrieved(movieEntity: MovieResponse?)
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
        ApiConfig().instance().getTvShow(id, BuildConfig.TOKEN)
            .enqueue(object : retrofit2.Callback<TvShowResponse> {
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    Log.d("M Fail", t.message.toString())
                }

                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    EspressoIdlingResource.decrement()
                    loadTvShowCallback.onTvShowRetrieved(response.body())
                }
            })
    }

    interface LoadTvShowCallback {
        fun onTvShowRetrieved(tvShowEntity: TvShowResponse?)
        fun onFail()
    }

}