package andi.gdk.jetpackpro.api

import andi.gdk.jetpackpro.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    private fun doRequest(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun instance(): MovieApi {
        return doRequest().create(MovieApi::class.java)
    }

}