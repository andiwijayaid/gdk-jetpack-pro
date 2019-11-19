package andi.gdk.jetpackpro.di

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.remote.RemoteRepository

object Injection {
    fun provideRepository(): TheMovieDbRepository? {
        val remoteRepository = RemoteRepository.getInstance()
        return TheMovieDbRepository.getInstance(remoteRepository)
    }
}