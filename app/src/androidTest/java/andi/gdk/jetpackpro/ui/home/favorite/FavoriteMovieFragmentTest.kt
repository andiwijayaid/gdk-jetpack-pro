package andi.gdk.jetpackpro.ui.home.favorite

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.testing.SingleFragmentActivity
import andi.gdk.jetpackpro.utils.EspressoIdlingResource
import andi.gdk.jetpackpro.utils.RecyclerViewItemCountAssertion
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteMovieFragmentTest {

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
    fun doUnFavoriteMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.viewPager)).perform(ViewActions.swipeLeft())
        Espresso.onView(ViewMatchers.withId(R.id.viewPager)).perform(ViewActions.swipeLeft())
        Espresso.onView(ViewMatchers.withId(R.id.favoriteMovieRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.favoriteBT))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favoriteBT)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        Espresso.onView(ViewMatchers.withId(R.id.favoriteMovieRV))
            .check(RecyclerViewItemCountAssertion(0))
    }

}