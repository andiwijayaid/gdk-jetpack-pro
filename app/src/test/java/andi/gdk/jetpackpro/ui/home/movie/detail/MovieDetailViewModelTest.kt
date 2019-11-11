package andi.gdk.jetpackpro.ui.home.movie.detail

import andi.gdk.jetpackpro.data.MovieEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieDetailViewModelTest {
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var dummyMovie: MovieEntity

    @Before
    fun setUp() {
        movieDetailViewModel = MovieDetailViewModel()
        dummyMovie = MovieEntity(
            "poster_a_start_is_born",
            "A Star is Rising",
            "October 3, 2018",
            75,
            135,
            36000000,
            433888866,
            "Seasoned musician Jackson Maine discovers  and falls in love with  struggling artist Ally. She has just about given up on her dream to make it big as a singer  until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons."
        )
    }

    @Test
    fun getMovie() {
        movieDetailViewModel.setMovieTitle(dummyMovie.title)
        val movie = movieDetailViewModel.getMovie()
        assertNotNull(movie)
        assertEquals(dummyMovie.poster, movie.poster)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.date, movie.date)
        assertEquals(dummyMovie.rating, movie.rating)
        assertEquals(dummyMovie.runtime, movie.runtime)
        assertEquals(dummyMovie.budget, movie.budget)
        assertEquals(dummyMovie.revenue, movie.revenue)
        assertEquals(dummyMovie.overview, movie.overview)
    }
}