package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.tvshow.adapter.TvShowAdapter
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
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter
    private var tvShowViewModel: TvShowViewModel? = null

    companion object {
        const val EXTRA_TV_SHOW_ID = "EXTRA_TV_SHOW_ID"
        const val EXTRA_TV_SHOW = "EXTRA_TV_SHOW"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvShowAdapter = TvShowAdapter(context)

        tvShowViewModel = obtainViewModel(activity)

        tvShowViewModel?.setUsername("The Movie DB")
        getTvShows()

        tvShowRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        tvShowRV.setHasFixedSize(true)
        tvShowRV.adapter = tvShowAdapter

        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                refreshLayout.finishLoadMore()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                getTvShows()
            }
        })
    }

    private fun getTvShows() {
        tvShowViewModel?.tvShows?.observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        tvShowAdapter.setTvShows(it.data)
                        tvShowAdapter.notifyDataSetChanged()
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

    private fun obtainViewModel(activity: FragmentActivity?): TvShowViewModel? {
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let {
            ViewModelProviders.of(it, factory).get(TvShowViewModel::class.java)
        }
    }
}