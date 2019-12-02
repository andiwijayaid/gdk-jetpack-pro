package andi.gdk.jetpackpro.data.source.local.room

import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface MovieDao {

    @WorkerThread
    @Query("SELECT * FROM movie")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateMovie(movie: MovieEntity)

}