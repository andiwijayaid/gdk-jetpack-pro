package andi.gdk.jetpackpro.data.source.local.room

import andi.gdk.jetpackpro.data.source.local.entity.MovieDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class, TvShowEntity::class, MovieDetailEntity::class, TvShowDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TheMovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun tvShowDetailDao(): TvShowDetailDao

    companion object {

        private val sLock = Any()
        private var INSTANCE: TheMovieDatabase? = null

        fun getInstance(context: Context): TheMovieDatabase? {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TheMovieDatabase::class.java,
                        "TheMovieDb.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

}