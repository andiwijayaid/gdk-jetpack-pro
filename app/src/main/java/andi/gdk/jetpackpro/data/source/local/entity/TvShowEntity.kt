package andi.gdk.jetpackpro.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowEntity(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("poster_path")
    var poster: String? = null,
    @SerializedName("backdrop_path")
    var backdrop: String? = null,
    @SerializedName("original_name")
    var title: String? = null,
    @SerializedName("first_air_date")
    var firstAirDate: String? = null,
    @SerializedName("vote_average")
    var rating: Float? = null,
    @SerializedName("overview")
    var overview: String? = null
) : Parcelable