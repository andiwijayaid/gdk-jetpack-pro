package andi.gdk.jetpackpro.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowResponse(
    @SerializedName("number_of_episodes")
    var numberOfEpisodes: Int?,

    @SerializedName("number_of_seasons")
    var numberOfSeasons: Int?
) : Parcelable