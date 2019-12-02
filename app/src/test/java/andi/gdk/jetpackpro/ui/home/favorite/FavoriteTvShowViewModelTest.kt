package andi.gdk.jetpackpro.ui.home.favorite

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.ui.home.favorite.tvshow.FavoriteTvShowViewModel
import andi.gdk.jetpackpro.vo.Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class FavoriteTvShowViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteTvShowViewModel
    private val theMovieDbRepository = Mockito.mock(TheMovieDbRepository::class.java)

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(theMovieDbRepository)
    }

    @Test
    fun getFavoriteTvShow() {
        val dummyTvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        val pagedList = Mockito.mock(
            PagedList::class.java
        ) as PagedList<TvShowEntity>

        dummyTvShows.value = Resource.success(pagedList)

        `when`(theMovieDbRepository.getFavoriteTvShows()).thenReturn(dummyTvShows)

        val observer =
            Mockito.mock(Observer::class.java) as Observer<Resource<PagedList<TvShowEntity>>>

        viewModel.favoriteTvShows.observeForever(observer)

        verify(observer).onChanged(Resource.success(pagedList))
    }

}