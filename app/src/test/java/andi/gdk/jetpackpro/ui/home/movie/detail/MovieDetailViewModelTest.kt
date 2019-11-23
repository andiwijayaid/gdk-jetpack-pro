package andi.gdk.jetpackpro.ui.home.movie.detail

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.response.MovieResponse
import andi.gdk.jetpackpro.utils.generateDummyMovie
import andi.gdk.jetpackpro.utils.generateDummyMovies
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MovieDetailViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieDetailViewModel
    private val theMovieDbRepository = mock(TheMovieDbRepository::class.java)
    private val dummyMovie = generateDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyMovieResponse = generateDummyMovie()

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(theMovieDbRepository)
        viewModel.setId(movieId)
    }

    @Test
    fun getMovie() {

        val movie = MutableLiveData<MovieResponse>()
        movie.value = dummyMovieResponse

        `when`<LiveData<MovieResponse>>(movieId?.let { theMovieDbRepository.getMovie(it) })
            .thenReturn(movie)

        val observer = mock(Observer::class.java) as Observer<MovieResponse>

        viewModel.setMovie()
        viewModel.movie.observeForever(observer)

        verify(observer).onChanged(dummyMovieResponse)

    }
}