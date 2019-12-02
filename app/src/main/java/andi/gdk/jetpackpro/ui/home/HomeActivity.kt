package andi.gdk.jetpackpro.ui.home

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.favorite.FavoriteFragment
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowFragment
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

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
        homeViewPagerAdapter.addFragment(FavoriteFragment(), getString(R.string.favorite))
        viewPager.adapter = homeViewPagerAdapter
    }
}
