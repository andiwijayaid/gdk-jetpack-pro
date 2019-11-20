package andi.gdk.jetpackpro.ui.home.detail

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowFragment.Companion.EXTRA_TV_SHOW
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowFragment.Companion.EXTRA_TV_SHOW_ID
import andi.gdk.jetpackpro.ui.home.tvshow.detail.TvShowDetailActivity
import andi.gdk.jetpackpro.utils.EspressoIdlingResource
import andi.gdk.jetpackpro.utils.generateFakeDummyTvShow
import andi.gdk.jetpackpro.utils.generateFakeDummyTvShows
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

class TvShowDetailActivityTest {

    private var dummyTvShow = generateFakeDummyTvShows()[0]
    private var dummyTvShowResponse = generateFakeDummyTvShow()

    @get: Rule
    var activityRule: ActivityTestRule<TvShowDetailActivity> = object :
        ActivityTestRule<TvShowDetailActivity>(TvShowDetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, TvShowDetailActivity::class.java)
            result.putExtra(EXTRA_TV_SHOW, dummyTvShow)
            result.putExtra(EXTRA_TV_SHOW_ID, dummyTvShow.id)
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
    fun loadTvShow() {
        onView(withId(R.id.titleTV)).check(matches(isDisplayed()))
        onView(withId(R.id.titleTV)).check(matches(withText(dummyTvShow.originalName)))

        onView(withId(R.id.dateTV)).check(matches(isDisplayed()))
        onView(withId(R.id.dateTV)).check(matches(withText(dummyTvShow.firstAirDate)))

        onView(withId(R.id.overviewTV)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewTV)).check(matches(withText("${dummyTvShow.overview} \n")))

        onView(withId(R.id.numberOfEpsTV)).check(matches(isDisplayed()))
        onView(withId(R.id.numberOfEpsTV)).check(matches(withText(dummyTvShowResponse.numberOfEpisodes.toString())))

        onView(withId(R.id.numberOfSeasonTV)).check(matches(isDisplayed()))
        onView(withId(R.id.numberOfSeasonTV)).check(matches(withText(dummyTvShowResponse.numberOfSeasons.toString())))
    }
}