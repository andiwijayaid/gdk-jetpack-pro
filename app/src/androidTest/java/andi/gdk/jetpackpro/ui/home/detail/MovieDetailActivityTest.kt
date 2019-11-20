package andi.gdk.jetpackpro.ui.home.detail

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment.Companion.EXTRA_MOVIE
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment.Companion.EXTRA_MOVIE_ID
import andi.gdk.jetpackpro.ui.home.movie.detail.MovieDetailActivity
import andi.gdk.jetpackpro.utils.EspressoIdlingResource
import andi.gdk.jetpackpro.utils.convertToCurrency
import andi.gdk.jetpackpro.utils.generateFakeDummyMovie
import andi.gdk.jetpackpro.utils.generateFakeDummyMovies
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailActivityTest {

    private var dummyMovie = generateFakeDummyMovies()[0]
    private var dummyMovieResponse = generateFakeDummyMovie()

    @get: Rule
    var activityRule: ActivityTestRule<MovieDetailActivity> = object :
        ActivityTestRule<MovieDetailActivity>(MovieDetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, MovieDetailActivity::class.java)
            result.putExtra(EXTRA_MOVIE, dummyMovie)
            result.putExtra(EXTRA_MOVIE_ID, dummyMovie.id)
            return result
        }
    }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.titleTV)).check(matches(isDisplayed()))
        onView(withId(R.id.titleTV)).check(matches(withText(dummyMovie.originalTitle)))

        onView(withId(R.id.dateTV)).check(matches(isDisplayed()))
        onView(withId(R.id.dateTV)).check(matches(withText(dummyMovie.releaseDate)))

        onView(withId(R.id.overviewTV)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewTV)).check(matches(withText("${dummyMovie.overview} \n")))

        onView(withId(R.id.budgetTV)).check(matches(isDisplayed()))
        onView(withId(R.id.budgetTV)).check(matches(withText(convertToCurrency(dummyMovieResponse.budget))))

        onView(withId(R.id.revenueTV)).check(matches(isDisplayed()))
        onView(withId(R.id.revenueTV)).check(matches(withText(convertToCurrency(dummyMovieResponse.revenue))))
    }
}