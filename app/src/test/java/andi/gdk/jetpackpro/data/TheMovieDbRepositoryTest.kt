package andi.gdk.jetpackpro.data

import andi.gdk.jetpackpro.data.source.FakeTheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.LocalRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowDetailEntity
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.data.source.remote.RemoteRepository
import andi.gdk.jetpackpro.utils.*
import andi.gdk.jetpackpro.vo.Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class TheMovieDbRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var local: LocalRepository = mock { }
    private var remote: RemoteRepository = mock { }
    private var instantAppExecutors: InstantAppExecutors = mock { }
    private val fakeTheMovieDbRepository =
        FakeTheMovieDbRepository(local, remote, instantAppExecutors)

    private val moviesResponse = generateDummyMovies()
    private val movieId = moviesResponse[0].id
    private val movieResponse = generateDummyMovie()

    private val tvShowsResponse = generateDummyTvShows()
    private val tvShowId = tvShowsResponse[0].id
    private val tvShowResponse = generateDummyTvShow()

    @Test
    fun getMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = generateDummyMovies()

        `when`(local.movies).thenReturn(dummyMovies)

        val result = LiveDataTestUtil.getValue(fakeTheMovieDbRepository.getMovies())

        verify(local).movies
        assertNotNull(result.data)
        assertEquals(moviesResponse.size, result.data?.size)
    }

    @Test
    fun getMovie() {
        val dummyMovie = MutableLiveData<MovieDetailEntity>()
        dummyMovie.value = generateDummyMovie()

        `when`(local.getMovieDetail(movieId)).thenReturn(dummyMovie)

        val result = LiveDataTestUtil.getValue(fakeTheMovieDbRepository.getMovie(movieId))

        verify(local).getMovieDetail(movieId)
        assertNotNull(result.data)
        assertEquals(movieResponse.id, result.data?.id)
        assertEquals(movieResponse.revenue, result.data?.revenue)
        assertEquals(movieResponse.budget, result.data?.budget)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = generateDummyTvShows()

        `when`(local.tvShows).thenReturn(dummyTvShows)

        val result = LiveDataTestUtil.getValue(fakeTheMovieDbRepository.getTvShows())

        verify(local).tvShows
        assertNotNull(result.data)
        assertEquals(tvShowsResponse.size, result.data?.size)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = MutableLiveData<TvShowDetailEntity>()
        dummyTvShow.value = generateDummyTvShow()

        `when`(tvShowId?.let { local.getTvShowDetail(it) }).thenReturn(dummyTvShow)

        val result = tvShowId?.let { fakeTheMovieDbRepository.getTvShow(it) }?.let {
            LiveDataTestUtil.getValue(
                it
            )
        }

        tvShowId?.let { verify(local).getTvShowDetail(it) }
        assertNotNull(result?.data)
        assertEquals(tvShowResponse.id, result?.data?.id)
        assertEquals(tvShowResponse.numberOfEpisodes, result?.data?.numberOfEpisodes)
        assertEquals(tvShowResponse.numberOfSeasons, result?.data?.numberOfSeasons)
    }

    @Test
    fun getFavoriteMovies() {

        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>

        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        fakeTheMovieDbRepository.getFavoriteMovies()
        val result = Resource.success(PagedListUtil.mockPagedList(moviesResponse))

        verify(local).getFavoriteMovies()
        assertNotNull(result?.data)
    }

    @Test
    fun getFavoriteTvShows() {

        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>

        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        fakeTheMovieDbRepository.getFavoriteTvShows()
        val result = Resource.success(PagedListUtil.mockPagedList(tvShowsResponse))

        verify(local).getFavoriteTvShows()
        assertNotNull(result?.data)
    }
}