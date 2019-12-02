package andi.gdk.jetpackpro.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tv_show_detail")
data class TvShowDetailEntity(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int? = null,

    @SerializedName("number_of_episodes")
    @ColumnInfo(name = "number_of_episodes")
    var numberOfEpisodes: Int?,

    @SerializedName("number_of_seasons")
    @ColumnInfo(name = "number_of_seasons")
    var numberOfSeasons: Int?
) : Parcelable