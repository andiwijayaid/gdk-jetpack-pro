package andi.gdk.jetpackpro.data.source.remote.response

import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowsResponse(
    @SerializedName("page")
    var page: Int?,

    @SerializedName("results")
    var tvShows: ArrayList<TvShowEntity>?
) : Parcelable