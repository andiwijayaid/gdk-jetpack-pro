package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.testing.SingleFragmentActivity
import andi.gdk.jetpackpro.utils.EspressoIdlingResource
import andi.gdk.jetpackpro.utils.RecyclerViewItemCountAssertion
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieFragmentTest {

    private lateinit var singleFragmentActivity: SingleFragmentActivity

    @get: Rule
    val activityRule = ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
        singleFragmentActivity = activityRule.activity
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
        onView(withId(R.id.movieRV)).check(matches(isDisplayed()))
        onView(withId(R.id.movieRV)).check(RecyclerViewItemCountAssertion(20))
    }

    @Test
    fun toDetailActivityTest() {
        onView(withId(R.id.movieRV))
            .check(matches(isDisplayed()))
        onView(withId(R.id.movieRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.titleTV)).check(matches(isDisplayed()))
    }

    @Test
    fun doFavoriteMovie() {
        onView(withId(R.id.movieRV))
            .check(matches(isDisplayed()))
        onView(withId(R.id.movieRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.favoriteBT)).check(matches(isDisplayed()))
        onView(withId(R.id.favoriteBT)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.favoriteMovieRV)).check(RecyclerViewItemCountAssertion(1))
    }
}