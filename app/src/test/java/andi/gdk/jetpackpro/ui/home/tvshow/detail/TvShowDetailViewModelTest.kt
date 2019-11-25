package andi.gdk.jetpackpro.ui.home.tvshow.detail

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.remote.response.TvShowResponse
import andi.gdk.jetpackpro.utils.generateDummyTvShow
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
import org.mockito.Mockito.`when`

class TvShowDetailViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowDetailViewModel
    private val theMovieDbRepository = Mockito.mock(TheMovieDbRepository::class.java)
    private var dummyTvShow = generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id
    private val dummyTvShowResponse = generateDummyTvShow()

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(theMovieDbRepository)
        viewModel.setId(tvShowId)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowResponse>()
        tvShow.value = dummyTvShowResponse

        `when`<LiveData<TvShowResponse>>(tvShowId?.let { theMovieDbRepository.getTvShow(it) })
            .thenReturn(tvShow)

        val observer = Mockito.mock(Observer::class.java) as Observer<TvShowResponse>

        viewModel.setTvShow()
        viewModel.tvShow.observeForever(observer)

        Mockito.verify(observer).onChanged(dummyTvShowResponse)
        TestCase.assertNotNull(viewModel.tvShow)
        TestCase.assertEquals(tvShow.value, dummyTvShowResponse)
    }
}