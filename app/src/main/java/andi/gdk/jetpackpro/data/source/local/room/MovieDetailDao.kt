package andi.gdk.jetpackpro.data.source.local.room

import andi.gdk.jetpackpro.data.source.local.entity.MovieDetailEntity
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDetailDao {

    @Transaction
    @Query("SELECT * FROM movie_detail WHERE id=:id")
    fun getMovieDetail(id: Int): LiveData<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movieDetail: MovieDetailEntity)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateMovieDetail(movieDetail: MovieDetailEntity)

}