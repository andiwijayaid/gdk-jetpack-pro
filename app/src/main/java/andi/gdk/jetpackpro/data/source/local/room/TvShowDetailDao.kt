package andi.gdk.jetpackpro.data.source.local.room

import andi.gdk.jetpackpro.data.source.local.entity.TvShowDetailEntity
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TvShowDetailDao {

    @Transaction
    @Query("SELECT * FROM tv_show_detail WHERE id=:id")
    fun getTvShowDetail(id: Int): LiveData<TvShowDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowDetail(tvShowDetail: TvShowDetailEntity)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateTvShowDetail(tvShowDetail: TvShowDetailEntity)

}