package andi.gdk.jetpackpro.data.source

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.api.ApiConfig
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.remote.response.TvShowResponse
import andi.gdk.jetpackpro.data.source.remote.response.TvShowsResponse
import android.util.Log
import retrofit2.Call
import retrofit2.Response

class TvShowData {

    fun loadTvShows(page: Int): ArrayList<TvShowEntity>? {

        var tvShows: ArrayList<TvShowEntity>? = arrayListOf()

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
                tvShows = response.body()?.tvShows
            }
        })

        return tvShows
    }

    fun loadTvShow(id: Int): TvShowEntity? {

        var tvShow: TvShowEntity? = null

        ApiConfig().instance().getTvShow(id, BuildConfig.TOKEN)
            .enqueue(object : retrofit2.Callback<TvShowResponse> {
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    Log.d("M Fail", t.message.toString())
                }

                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
//                    tvShow = TvShowEntity(
//                        numberOfEpisode = response.body()?.numberOfEpisodes,
//                        numberOfSeason = response.body()?.numberOfSeasons
//                    )
                }
            })

        return tvShow
    }

}