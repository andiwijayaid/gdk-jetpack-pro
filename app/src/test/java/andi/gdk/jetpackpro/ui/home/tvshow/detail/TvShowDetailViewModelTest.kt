package andi.gdk.jetpackpro.ui.home.tvshow.detail

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.TvShowDetailEntity
import andi.gdk.jetpackpro.utils.generateDummyTvShow
import andi.gdk.jetpackpro.utils.generateDummyTvShows
import andi.gdk.jetpackpro.vo.Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(theMovieDbRepository)
        viewModel.setId(tvShowId)
    }

    @Test
    fun getTvShow() {
        val resource = Resource.success(generateDummyTvShow())
        val tvShowDetailEntity = MutableLiveData<Resource<TvShowDetailEntity>>()
        tvShowDetailEntity.value = resource

        `when`(tvShowId?.let { theMovieDbRepository.getTvShow(it) }).thenReturn(tvShowDetailEntity)

        val observer = Mockito.mock(Observer::class.java) as Observer<Resource<TvShowDetailEntity>>
        viewModel.tvShow.observeForever(observer)
        Mockito.verify(observer).onChanged(resource)
    }
}