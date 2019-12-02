package andi.gdk.jetpackpro.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tv_show")
data class TvShowEntity(

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int? = null,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("original_name")
    @ColumnInfo(name = "original_name")
    var originalName: String? = null,

    @SerializedName("first_air_date")
    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = null,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage: Float? = null,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String? = null,

    var isFavorite: Boolean? = false

) : Parcelable