package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.utils.generateDummyTvShows
import andi.gdk.jetpackpro.vo.Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TvShowViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowViewModel
    private val theMovieDbRepository = Mockito.mock(TheMovieDbRepository::class.java)
    private val username = "The Movie DB"

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(theMovieDbRepository)
    }

    @Test
    fun getTvShows() {
        val resource = Resource.success(generateDummyTvShows()) as Resource<List<TvShowEntity>>
        val dummyTvShows = MutableLiveData<Resource<List<TvShowEntity>>>()
        dummyTvShows.value = resource

        Mockito.`when`(theMovieDbRepository.getTvShows()).thenReturn(dummyTvShows)

        val observer = Mockito.mock(Observer::class.java) as Observer<Resource<List<TvShowEntity>>>
        viewModel.setUsername(username)
        viewModel.tvShows.observeForever(observer)
        verify(observer).onChanged(resource)
    }
}