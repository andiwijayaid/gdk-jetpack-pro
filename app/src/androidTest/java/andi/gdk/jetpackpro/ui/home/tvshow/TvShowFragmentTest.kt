package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.testing.SingleFragmentActivity
import andi.gdk.jetpackpro.utils.RecyclerViewItemCountAssertion
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFragmentTest {

    private lateinit var singleFragmentActivity: SingleFragmentActivity

    @get: Rule
    val activityRule = ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java)

    @Before
    fun setUp() {
        singleFragmentActivity = activityRule.activity
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.tvShowRV)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowRV)).check(RecyclerViewItemCountAssertion(10))
    }

    @Test
    fun toDetailActivityTest() {
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft())

        onView(withId(R.id.tvShowRV))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvShowRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.titleTV)).check(matches(isDisplayed()))
        onView(withId(R.id.titleTV)).check(matches(ViewMatchers.withText("Arrow")))
    }
}