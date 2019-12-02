package andi.gdk.jetpackpro.data.source.local.room

import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TvShowDao {

    @WorkerThread
    @Query("SELECT * FROM tv_show")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateTvShow(tvShow: TvShowEntity)

}