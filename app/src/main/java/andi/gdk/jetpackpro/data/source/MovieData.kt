package andi.gdk.jetpackpro.data.source

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.api.ApiConfig
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.remote.response.MoviesResponse
import andi.gdk.jetpackpro.response.MovieResponse
import android.util.Log
import retrofit2.Call
import retrofit2.Response

class MovieData {

    fun loadMovies(page: Int): ArrayList<MovieEntity>? {

        var movies: ArrayList<MovieEntity>? = arrayListOf()

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
                movies = response.body()?.movies
            }
        })

        return movies
    }

    fun loadMovie(id: Int): MovieEntity? {

        var movie: MovieEntity? = null

        ApiConfig().instance().getMovie(id, BuildConfig.TOKEN)
            .enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("M Fail", t.message.toString())
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
//                    movie = MovieEntity(
//                        budget = response.body()?.budget,
//                        revenue = response.body()?.revenue,
//                        runtime = response.body()?.runtime
//                    )
                }
            })

        return movie
    }

}