package andi.gdk.jetpackpro.ui.home.favorite.tvshow

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.favorite.tvshow.adapter.FavoriteTvShowAdapter
import andi.gdk.jetpackpro.viewmodel.ViewModelFactory
import andi.gdk.jetpackpro.vo.Status
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*

class FavoriteTvShowFragment : Fragment() {

    private var favoriteTvShowViewModel: FavoriteTvShowViewModel? = null
    private lateinit var favoriteTvShowAdapter: FavoriteTvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        favoriteTvShowAdapter = FavoriteTvShowAdapter(activity?.applicationContext)

        favoriteTvShowViewModel = obtainViewModel(activity)

        setFavoriteTvShows()

        favoriteTvShowRV.layoutManager = GridLayoutManager(context, 3)
        favoriteTvShowAdapter.notifyDataSetChanged()
        favoriteTvShowRV.adapter = favoriteTvShowAdapter
    }

    private fun obtainViewModel(activity: FragmentActivity?): FavoriteTvShowViewModel? {
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let {
            ViewModelProviders.of(it, factory).get(FavoriteTvShowViewModel::class.java)
        }
    }

    private fun setFavoriteTvShows() {
        favoriteTvShowViewModel?.favoriteTvShows?.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    favoriteTvShowAdapter.submitList(it.data)
                    favoriteTvShowAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(
                        context,
                        getString(R.string.something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

}