package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.utils.generateDummyTvShows
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TvShowViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowViewModel
    private val theMovieDbRepository = Mockito.mock(TheMovieDbRepository::class.java)

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(theMovieDbRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = generateDummyTvShows()

        val tvShows = MutableLiveData<ArrayList<TvShowEntity>>()
        tvShows.value = dummyTvShows

        Mockito.`when`<LiveData<ArrayList<TvShowEntity>>>(theMovieDbRepository.getTvShows(1))
            .thenReturn(
                tvShows
            )

        val observer = Mockito.mock(Observer::class.java) as Observer<ArrayList<TvShowEntity>>

        viewModel.setPage(1)
        viewModel.setTvShows()
        viewModel.tvShows.observeForever(observer)

        Mockito.verify(observer).onChanged(dummyTvShows)
        TestCase.assertNotNull(viewModel.tvShows)
        TestCase.assertEquals(tvShows.value?.size, viewModel.countRetrievedTvShows())
    }
}