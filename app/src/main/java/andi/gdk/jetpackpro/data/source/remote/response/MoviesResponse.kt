package andi.gdk.jetpackpro.data.source.remote.response

import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesResponse(
    @SerializedName("page")
    var page: Int?,

    @SerializedName("results")
    var movies: ArrayList<MovieEntity>?
) : Parcelable