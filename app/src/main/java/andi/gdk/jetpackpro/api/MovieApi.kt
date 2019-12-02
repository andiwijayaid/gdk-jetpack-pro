package andi.gdk.jetpackpro.api

import andi.gdk.jetpackpro.data.source.local.entity.TvShowDetailEntity
import andi.gdk.jetpackpro.data.source.remote.response.MoviesResponse
import andi.gdk.jetpackpro.data.source.remote.response.TvShowsResponse
import andi.gdk.jetpackpro.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Call<MoviesResponse>

    @GET("3/discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ): Call<TvShowsResponse>

    @GET("3/movie/{id}")
    fun getMovie(
        @Path("id") id: Int?,
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("3/tv/{id}")
    fun getTvShow(
        @Path("id") id: Int?,
        @Query("api_key") apiKey: String
    ): Call<TvShowDetailEntity>
}