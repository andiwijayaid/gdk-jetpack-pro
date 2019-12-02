package andi.gdk.jetpackpro.ui.home.favorite

import andi.gdk.jetpackpro.data.source.TheMovieDbRepository
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.ui.home.favorite.movie.FavoriteMovieViewModel
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

class FavoriteMovieViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteMovieViewModel
    private val theMovieDbRepository = Mockito.mock(TheMovieDbRepository::class.java)

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(theMovieDbRepository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyMovies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        val pagedList = Mockito.mock(
            PagedList::class.java
        ) as PagedList<MovieEntity>

        dummyMovies.value = Resource.success(pagedList)

        `when`(theMovieDbRepository.getFavoriteMovies()).thenReturn(dummyMovies)

        val observer =
            Mockito.mock(Observer::class.java) as Observer<Resource<PagedList<MovieEntity>>>

        viewModel.favoriteMovies.observeForever(observer)

        verify(observer).onChanged(Resource.success(pagedList))
    }

}