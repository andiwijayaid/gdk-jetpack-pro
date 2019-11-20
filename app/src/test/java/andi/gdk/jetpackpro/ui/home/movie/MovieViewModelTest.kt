package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.utils.generateDummyMovies
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MovieViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel
    private val theMovieDbRepository = mock(TheMovieDbRepository::class.java)

    @Before
    fun setUp() {
        viewModel = MovieViewModel(theMovieDbRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = generateDummyMovies()

        val movies = MutableLiveData<ArrayList<MovieEntity>>()
        movies.value = dummyMovies

        `when`<LiveData<ArrayList<MovieEntity>>>(theMovieDbRepository.getMovies(1)).thenReturn(
            movies
        )

        val observer = mock(Observer::class.java) as Observer<ArrayList<MovieEntity>>

        viewModel.setPage(1)
        viewModel.movies.observeForever(observer)

        verify(observer).onChanged(dummyMovies)
    }
}