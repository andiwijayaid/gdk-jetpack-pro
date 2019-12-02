package andi.gdk.jetpackpro.ui.home.favorite

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.favorite.movie.FavoriteMovieFragment
import andi.gdk.jetpackpro.ui.home.favorite.tvshow.FavoriteTvShowFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {

    private lateinit var favoriteView: View
    private lateinit var favoriteMovieFragment: FavoriteMovieFragment
    private lateinit var favoriteTvShowFragment: FavoriteTvShowFragment
    private lateinit var activeFragment: Fragment
    private var currentTabPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        favoriteView = inflater.inflate(R.layout.fragment_favorite, container, false)

        favoriteMovieFragment =
            FavoriteMovieFragment()
        favoriteTvShowFragment = FavoriteTvShowFragment()
        activeFragment = favoriteMovieFragment
        selectFragment(activeFragment)
        setupTabLayout()
        bindWidgetWithEvent()

        return favoriteView
    }

    private fun bindWidgetWithEvent() {
        favoriteView.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    currentTabPosition = p0.position
                }
                setCurrentTabFragment(p0?.position)
            }

        })
    }

    private fun setCurrentTabFragment(position: Int?) {
        when (position) {
            0 -> selectFragment(favoriteMovieFragment)
            1 -> selectFragment(favoriteTvShowFragment)
        }
    }

    private fun selectFragment(fragment: Fragment) {
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        activeFragment = fragment
    }

    private fun setupTabLayout() {
        favoriteView.tabLayout.addTab(
            favoriteView.tabLayout.newTab().setText(getString(R.string.movie)),
            true
        )
        favoriteView.tabLayout.addTab(favoriteView.tabLayout.newTab().setText(getString(R.string.tv_show)))
    }

}