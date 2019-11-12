package andi.gdk.jetpackpro.ui.home.detail

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowFragment.Companion.EXTRA_TV_SHOW_TITLE
import andi.gdk.jetpackpro.ui.home.tvshow.detail.TvShowDetailActivity
import andi.gdk.jetpackpro.utils.generateFakeDummyTvShows
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class TvShowDetailActivityTest {

    private var dummyTvShow = generateFakeDummyTvShows()[0]

    @get: Rule
    var activityRule: ActivityTestRule<TvShowDetailActivity> = object :
        ActivityTestRule<TvShowDetailActivity>(TvShowDetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, TvShowDetailActivity::class.java)
            result.putExtra(EXTRA_TV_SHOW_TITLE, dummyTvShow.title)
            return result
        }
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.titleTV)).check(matches(isDisplayed()))
        onView(withId(R.id.titleTV)).check(matches(withText(dummyTvShow.title)))

        onView(withId(R.id.dateTV)).check(matches(isDisplayed()))
        onView(withId(R.id.dateTV)).check(matches(withText(dummyTvShow.date)))

        onView(withId(R.id.overviewTV)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewTV)).check(matches(withText("${dummyTvShow.overview} \n")))
    }
}