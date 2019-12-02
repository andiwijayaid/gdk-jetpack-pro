package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.movie.adapter.MovieAdapter
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private var movieViewModel: MovieViewModel? = null

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
        const val EXTRA_MOVIE_DETAIL = "EXTRA_MOVIE_DETAIL"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieAdapter = MovieAdapter(context)

        movieViewModel = obtainViewModel(activity)

        movieViewModel?.setUsername("The Movie DB")
        getMovies()

        movieRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        movieRV.setHasFixedSize(true)
        movieRV.adapter = movieAdapter

        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                refreshLayout.finishLoadMore()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                getMovies()
            }
        })
    }

    private fun getMovies() {
        movieViewModel?.movies?.observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        movieAdapter.setMovies(it.data)
                        movieAdapter.notifyDataSetChanged()
                        refreshLayout.finishRefresh()
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        onFailLL.visibility = View.VISIBLE
                        Toast.makeText(
                            context,
                            resources.getString(R.string.check_your_connection),
                            Toast.LENGTH_LONG
                        ).show()
                        refreshLayout.finishRefresh()
                    }
                }
            }
        })
    }

    private fun obtainViewModel(activity: FragmentActivity?): MovieViewModel? {
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(MovieViewModel::class.java) }
    }
}