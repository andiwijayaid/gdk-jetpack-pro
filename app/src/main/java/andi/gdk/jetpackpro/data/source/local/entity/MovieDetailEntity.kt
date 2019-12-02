package andi.gdk.jetpackpro.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_detail")
data class MovieDetailEntity(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int? = null,

    @SerializedName("budget")
    @ColumnInfo(name = "budget")
    var budget: String?,

    @SerializedName("revenue")
    @ColumnInfo(name = "revenue")
    var revenue: String?,

    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    var runtime: Int?,

    var isFavorite: Boolean = false
) : Parcelable