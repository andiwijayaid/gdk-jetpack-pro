package andi.gdk.jetpackpro.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("original_title")
    var originalTitle: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Float? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("adult")
    var isAdult: Boolean? = null
) : Parcelable