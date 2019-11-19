package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.ui.home.tvshow.adapter.TvShowAdapter
import andi.gdk.jetpackpro.viewmodel.ViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : androidx.fragment.app.Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter
    private var tvShowViewModel: TvShowViewModel? = null
    private var page = 1

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
        showLoading(true)

        tvShowViewModel = obtainViewModel(activity)
        tvShowViewModel?.setPage(page)
        setTvShows()

        tvShowRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        tvShowRV.setHasFixedSize(true)
        tvShowRV.adapter = tvShowAdapter

        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page += 1
                tvShowViewModel?.setPage(page)
                setTvShows()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                tvShowViewModel?.setPage(page)
                setTvShows()
            }
        })
    }

    private fun setTvShows() {

        tvShowViewModel?.tvShows?.observe(this, androidx.lifecycle.Observer {
            showLoading(false)

            refreshLayout.finishRefresh(true)
            refreshLayout.finishLoadMore(true)
            if (it != null) {
                if (page == 1) {
                    tvShowAdapter.setTvShows(it)
                } else {
                    tvShowAdapter.addTvShows(it)
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

    private fun obtainViewModel(activity: FragmentActivity?): TvShowViewModel? {
        val factory = ViewModelFactory.getInstance()
        return activity?.let {
            ViewModelProviders.of(it, factory).get(TvShowViewModel::class.java)
        }
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

}