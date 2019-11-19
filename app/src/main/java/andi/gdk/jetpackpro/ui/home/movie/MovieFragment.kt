package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.movie.adapter.MovieAdapter
import andi.gdk.jetpackpro.viewmodel.ViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    private var page = 1

    companion object {
        const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
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
        showLoading(true)

        movieViewModel = obtainViewModel(activity)
        movieViewModel.setPage(page)
        setMovies()

        movieRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        movieRV.setHasFixedSize(true)
        movieRV.adapter = movieAdapter

        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page += 1
                movieViewModel.setPage(page)
                setMovies()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                movieViewModel.setPage(page)
                setMovies()
            }
        })
    }

    private fun setMovies() {
        movieViewModel.movies.observe(this, androidx.lifecycle.Observer {
            showLoading(false)

            refreshLayout.finishRefresh(true)
            refreshLayout.finishLoadMore(true)
            if (it != null) {
                if (page == 1) {
                    movieAdapter.setMovies(it)
                } else {
                    movieAdapter.addMovies(it)
                }
                onFailLL.visibility = View.GONE
            } else {
                onFailLL.visibility = View.VISIBLE
                Toast.makeText(
                    context,
                    resources.getString(R.string.check_your_connection),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun obtainViewModel(activity: FragmentActivity?): MovieViewModel {
        val factory = ViewModelFactory.getInstance()
        return activity?.let {
            ViewModelProviders.of(it, factory).get(MovieViewModel::class.java)
        }!!
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}