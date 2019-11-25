package andi.gdk.jetpackpro.data

import andi.gdk.jetpackpro.data.source.FakeTheMovieDbRepository
import andi.gdk.jetpackpro.data.source.remote.RemoteRepository
import andi.gdk.jetpackpro.utils.LiveDataTestUtil
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class TheMovieDbRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var remote: RemoteRepository = mock { }
    private val theMovieDbRepository = FakeTheMovieDbRepository(remote)

    private val moviesResponse = generateDummyMovies()
    private val movieId = moviesResponse[0].id
    private val movieResponse = generateDummyMovie()

    private val tvShowsResponse = generateDummyTvShows()
    private val tvShowId = tvShowsResponse[0].id
    private val tvShowResponse = generateDummyTvShow()

    private val page = 1

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getMovies() {
        doAnswer {
            (it.arguments[1] as RemoteRepository.LoadMoviesCallback)
                .onMoviesRetrieved(moviesResponse)
            null
        }.`when`(remote).getMovies(any(), eq(page))

        val result = LiveDataTestUtil.getValue(theMovieDbRepository.getMovies(page))

        assertNotNull(result)
        assertEquals(moviesResponse.size, result.size)
    }

    @Test
    fun getMovie() {
        doAnswer {
            (it.arguments[1] as RemoteRepository.LoadMovieCallback)
                .onMovieRetrieved(movieResponse)
            null
        }.`when`(remote).getMovie(any(), eq(movieId))

        val result = LiveDataTestUtil.getValue(theMovieDbRepository.getMovie(movieId))
        assertNotNull(result)
    }

    @Test
    fun getTvShows() {
        doAnswer {
            (it.arguments[1] as RemoteRepository.LoadTvShowsCallback)
                .onTvShowsRetrieved(tvShowsResponse)
            null
        }.`when`(remote).getTvShows(any(), eq(page))

        val result = LiveDataTestUtil.getValue(theMovieDbRepository.getTvShows(page))

        assertNotNull(result)
        assertEquals(tvShowsResponse.size, result.size)
    }

    @Test
    fun getTvShow() {
        doAnswer {
            (it.arguments[1] as RemoteRepository.LoadTvShowCallback)
                .onTvShowRetrieved(tvShowResponse)
            null
        }.`when`(remote).getTvShow(any(), eq(tvShowId))

        val result = LiveDataTestUtil.getValue(theMovieDbRepository.getTvShow(tvShowId))
        assertNotNull(result)
    }
}