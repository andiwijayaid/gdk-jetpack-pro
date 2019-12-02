package andi.gdk.jetpackpro.data.source.local.room

import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface TvShowDao {

    @WorkerThread
    @Query("SELECT * FROM tv_show")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show WHERE isFavorite = 1")
    fun getFavotiteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateTvShow(tvShow: TvShowEntity)

}