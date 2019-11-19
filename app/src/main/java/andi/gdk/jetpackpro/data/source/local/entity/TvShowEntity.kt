package andi.gdk.jetpackpro.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowEntity(
    val poster: String? = null,
    val title: String? = null,
    val date: String? = null,
    val rating: Int? = null,
    val runtime: Int? = null,
    val numberOfEpisode: Int? = null,
    val language: String? = null,
    val overview: String? = null,
    val numberOfSeason: Int? = null
) : Parcelable