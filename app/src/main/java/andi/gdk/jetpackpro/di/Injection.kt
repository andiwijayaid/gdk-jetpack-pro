package andi.gdk.jetpackpro.di

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.LocalRepository
import andi.gdk.jetpackpro.data.source.local.room.TheMovieDatabase
import andi.gdk.jetpackpro.data.source.remote.RemoteRepository
import andi.gdk.jetpackpro.utils.AppExecutors
import android.app.Application

object Injection {
    fun provideRepository(application: Application): TheMovieDbRepository? {

        val database = TheMovieDatabase.getInstance(application)

        val remoteRepository = RemoteRepository.getInstance()
        val localRepository = LocalRepository.getInstance(
            database?.movieDao(),
            database?.tvShowDao(),
            database?.movieDetailDao(),
            database?.tvShowDetailDao()
        )
        val appExecutors = AppExecutors()
        return TheMovieDbRepository.getInstance(localRepository, remoteRepository, appExecutors)
    }
}