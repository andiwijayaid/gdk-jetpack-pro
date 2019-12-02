package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.utils.generateDummyMovies
import andi.gdk.jetpackpro.vo.Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel
    private val theMovieDbRepository = mock(TheMovieDbRepository::class.java)
    private val username = "The Movie DB"

    @Before
    fun setUp() {
        viewModel = MovieViewModel(theMovieDbRepository)
    }

    @Test
    fun getMovies() {
        val resource = Resource.success(generateDummyMovies()) as Resource<List<MovieEntity>>
        val dummyMovies = MutableLiveData<Resource<List<MovieEntity>>>()
        dummyMovies.value = resource

        `when`(theMovieDbRepository.getMovies()).thenReturn(dummyMovies)

        val observer = mock(Observer::class.java) as Observer<Resource<List<MovieEntity>>>
        viewModel.setUsername(username)
        viewModel.movies.observeForever(observer)
        verify(observer).onChanged(resource)
    }
}