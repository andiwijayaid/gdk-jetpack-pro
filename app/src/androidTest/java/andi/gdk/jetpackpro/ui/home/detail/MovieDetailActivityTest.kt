package andi.gdk.jetpackpro.ui.home.detail

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.movie.detail.MovieDetailActivity
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class MovieDetailActivityTest {

    private var dummyMovie = generateFakeDummyMovies()[0]

    @get: Rule
    var activityRule: ActivityTestRule<MovieDetailActivity> = object :
        ActivityTestRule<MovieDetailActivity>(MovieDetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, MovieDetailActivity::class.java)
            result.putExtra(EXTRA_MOVIE_TITLE, dummyMovie.title)
            return result
        }
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.titleTV)).check(matches(isDisplayed()))
        onView(withId(R.id.titleTV)).check(matches(withText(dummyMovie.title)))

        onView(withId(R.id.dateTV)).check(matches(isDisplayed()))
        onView(withId(R.id.dateTV)).check(matches(withText(dummyMovie.date)))

        onView(withId(R.id.overviewTV)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewTV)).check(matches(withText("${dummyMovie.overview} \n")))
    }
}