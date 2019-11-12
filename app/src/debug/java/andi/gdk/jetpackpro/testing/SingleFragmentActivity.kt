package andi.gdk.jetpackpro.testing

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.HomeViewPagerAdapter
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowFragment
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class SingleFragmentActivity : AppCompatActivity() {

    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.elevation = 0f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBar.outlineProvider = null
        }

        homeViewPagerAdapter = HomeViewPagerAdapter(supportFragmentManager)
        setupViewPager()
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager() {
        homeViewPagerAdapter.addFragment(MovieFragment(), getString(R.string.movie))
        homeViewPagerAdapter.addFragment(TvShowFragment(), getString(R.string.tv_show))
        viewPager.adapter = homeViewPagerAdapter
    }
}