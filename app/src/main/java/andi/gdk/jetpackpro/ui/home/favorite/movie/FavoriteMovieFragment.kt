package andi.gdk.jetpackpro.ui.home.favorite.movie

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.favorite.movie.adapter.FavoriteMovieAdapter
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
import kotlinx.android.synthetic.main.fragment_favorite_movie.*

class FavoriteMovieFragment : Fragment() {

    private var favoriteMovieViewModel: FavoriteMovieViewModel? = null
    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        favoriteMovieAdapter = FavoriteMovieAdapter(activity?.applicationContext)

        favoriteMovieViewModel = obtainViewModel(activity)

        setFavoriteMovies()

        favoriteMovieRV.layoutManager = GridLayoutManager(context, 3)
        favoriteMovieAdapter.notifyDataSetChanged()
        favoriteMovieRV.adapter = favoriteMovieAdapter
    }

    private fun obtainViewModel(activity: FragmentActivity?): FavoriteMovieViewModel? {
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let {
            ViewModelProviders.of(it, factory).get(FavoriteMovieViewModel::class.java)
        }
    }

    private fun setFavoriteMovies() {
        favoriteMovieViewModel?.favoriteMovies?.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    favoriteMovieAdapter.submitList(it.data)
                    favoriteMovieAdapter.notifyDataSetChanged()
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