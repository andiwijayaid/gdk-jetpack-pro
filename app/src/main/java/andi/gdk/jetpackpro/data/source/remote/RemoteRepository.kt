package andi.gdk.jetpackpro.data.source.remote

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.api.ApiConfig
import andi.gdk.jetpackpro.data.source.local.entity.MovieDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.remote.response.MoviesResponse
import andi.gdk.jetpackpro.data.source.remote.response.TvShowsResponse
import andi.gdk.jetpackpro.response.MovieResponse
import andi.gdk.jetpackpro.utils.EspressoIdlingResource
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getMovies(): LiveData<ApiResponse<List<MovieEntity>>> {

        EspressoIdlingResource.increment()
        val movies: MutableLiveData<ApiResponse<List<MovieEntity>>> = MutableLiveData()

        ApiConfig().instance().getMovies(
            BuildConfig.TOKEN
        ).enqueue(object : retrofit2.Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.d("M Fail", t.message.toString())
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                movies.value = ApiResponse.success(response.body()?.movies)
                if (!EspressoIdlingResource.espressoIdlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        })
        return movies
    }

    fun getMovie(id: Int?): LiveData<ApiResponse<MovieDetailEntity>> {

        EspressoIdlingResource.increment()
        val movie: MutableLiveData<ApiResponse<MovieDetailEntity>> = MutableLiveData()

        ApiConfig().instance().getMovie(id, BuildConfig.TOKEN)
            .enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("M Fail", t.message.toString())
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    movie.value = ApiResponse.success(
                        MovieDetailEntity(
                            id,
                            response.body()?.budget,
                            response.body()?.revenue,
                            response.body()?.runtime
                        )
                    )
                    if (!EspressoIdlingResource.espressoIdlingResource.isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            })

        return movie
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShowEntity>>> {

        EspressoIdlingResource.increment()
        val tvShows: MutableLiveData<ApiResponse<List<TvShowEntity>>> = MutableLiveData()

        ApiConfig().instance().getTvShows(
            BuildConfig.TOKEN
        ).enqueue(object : retrofit2.Callback<TvShowsResponse> {
            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.d("M Fail", t.message.toString())
            }

            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                tvShows.value = ApiResponse.success(response.body()?.tvShows)
                if (!EspressoIdlingResource.espressoIdlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        })

        return tvShows
    }

    fun getTvShow(id: Int?): LiveData<ApiResponse<TvShowDetailEntity>> {

        EspressoIdlingResource.increment()
        val tvShow: MutableLiveData<ApiResponse<TvShowDetailEntity>> = MutableLiveData()

        ApiConfig().instance().getTvShow(id, BuildConfig.TOKEN)
            .enqueue(object : retrofit2.Callback<TvShowDetailEntity> {
                override fun onFailure(call: Call<TvShowDetailEntity>, t: Throwable) {
                    Log.d("M Fail", t.message.toString())
                }

                override fun onResponse(
                    call: Call<TvShowDetailEntity>,
                    response: Response<TvShowDetailEntity>
                ) {
                    tvShow.value = ApiResponse.success(response.body())
                    if (!EspressoIdlingResource.espressoIdlingResource.isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            })
        return tvShow
    }

}