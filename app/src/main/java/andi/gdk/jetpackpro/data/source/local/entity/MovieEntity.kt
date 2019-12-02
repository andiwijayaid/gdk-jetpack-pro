package andi.gdk.jetpackpro.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage: Float? = null,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @SerializedName("adult")
    @ColumnInfo(name = "is_adult")
    var isAdult: Boolean? = null,

    var isFavorite: Boolean = false

) : Parcelable