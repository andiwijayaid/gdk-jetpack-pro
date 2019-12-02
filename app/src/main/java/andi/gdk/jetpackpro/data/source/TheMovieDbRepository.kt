package andi.gdk.jetpackpro.data.source

import andi.gdk.jetpackpro.data.source.local.LocalRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.remote.ApiResponse
import andi.gdk.jetpackpro.data.source.remote.RemoteRepository
import andi.gdk.jetpackpro.utils.AppExecutors
import andi.gdk.jetpackpro.vo.Resource
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

open class TheMovieDbRepository private constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    val appExecutors: AppExecutors
) :
    TheMovieDbDataSource {

    companion object {
        @Volatile
        private var INSTANCE: TheMovieDbRepository? = null

        fun getInstance(
            localRepository: LocalRepository?,
            remoteRepository: RemoteRepository,
            appExecutors: AppExecutors
        ): TheMovieDbRepository? {
            if (INSTANCE == null) {
                synchronized(TheMovieDbRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = localRepository?.let {
                            TheMovieDbRepository(
                                it,
                                remoteRepository,
                                appExecutors
                            )
                        }
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {

        return object : NetworkBoundResource<List<MovieEntity>, List<MovieEntity>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localRepository.movies!!
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return data?.size == 0
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> {
                return remoteRepository.getMovies()
            }

            override fun saveCallResult(data: List<MovieEntity>?) {
                data?.let { localRepository.insertMovies(it) }
            }

        }.asLiveData()

    }

    override fun getMovie(id: Int): LiveData<Resource<MovieDetailEntity>> {

        return object : NetworkBoundResource<MovieDetailEntity, MovieDetailEntity>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieDetailEntity> {
                return localRepository.getMovieDetail(id)!!
            }

            override fun shouldFetch(data: MovieDetailEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetailEntity>> {
                return remoteRepository.getMovie(id)
            }

            override fun saveCallResult(data: MovieDetailEntity?) {
                data?.let { localRepository.insertMovieDetail(it) }
            }
        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<TvShowEntity>>> {

        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowEntity>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localRepository.tvShows!!
            }

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean {
                return data?.size == 0
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowEntity>>> {
                return remoteRepository.getTvShows()
            }

            override fun saveCallResult(data: List<TvShowEntity>?) {
                data?.let { localRepository.insertTvShows(it) }
            }

        }.asLiveData()

    }

    override fun getTvShow(id: Int): LiveData<Resource<TvShowDetailEntity>> {

        return object : NetworkBoundResource<TvShowDetailEntity, TvShowDetailEntity>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowDetailEntity> {
                return localRepository.getTvShowDetail(id)!!
            }

            override fun shouldFetch(data: TvShowDetailEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<TvShowDetailEntity>> {
                return remoteRepository.getTvShow(id)
            }

            override fun saveCallResult(data: TvShowDetailEntity?) {
                data?.let { localRepository.insertTvShowDetail(it) }
            }
        }.asLiveData()

    }

    override fun getFavoriteMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieEntity>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder(
                    localRepository.getFavoriteMovies() as DataSource.Factory<Int, MovieEntity>,
                    20
                ).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>>? {
                return null
            }

            override fun saveCallResult(data: List<MovieEntity>?) {

            }

        }.asLiveData()
    }

    override fun setFavoriteMovie(movieEntity: MovieEntity) {
        val runnable = Runnable { localRepository.setFavoriteMovie(movieEntity) }
        appExecutors.diskIO().execute(runnable)
    }

    override fun setFavoriteMovieDetail(movieDetailEntity: MovieDetailEntity) {
        val runnable = Runnable { localRepository.setFavoriteMovieDetail(movieDetailEntity) }
        appExecutors.diskIO().execute(runnable)
    }

}