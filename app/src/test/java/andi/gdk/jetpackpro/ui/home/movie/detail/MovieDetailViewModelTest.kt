package andi.gdk.jetpackpro.ui.home.movie.detail

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieDetailEntity
import andi.gdk.jetpackpro.utils.generateDummyMovie
import andi.gdk.jetpackpro.utils.generateDummyMovies
import andi.gdk.jetpackpro.vo.Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(theMovieDbRepository)
        viewModel.setId(movieId)
    }

    @Test
    fun getMovie() {
        val resource = Resource.success(generateDummyMovie())
        val movieDetailEntity = MutableLiveData<Resource<MovieDetailEntity>>()
        movieDetailEntity.value = resource

        `when`(theMovieDbRepository.getMovie(movieId)).thenReturn(movieDetailEntity)

        val observer = mock(Observer::class.java) as Observer<Resource<MovieDetailEntity>>
        viewModel.movie.observeForever(observer)
        verify(observer).onChanged(resource)
    }
}