package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.TvShowEntity
import andi.gdk.jetpackpro.ui.home.tvshow.adapter.TvShowAdapter
import andi.gdk.jetpackpro.ui.home.tvshow.detail.TvShowDetailActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : androidx.fragment.app.Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter
    private var tvShows: ArrayList<TvShowEntity> = arrayListOf()
    private lateinit var tvShowViewModel: TvShowViewModel

    companion object {
        const val EXTRA_TV_SHOW_TITLE = "EXTRA_TV_SHOW_TITLE"
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

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        tvShows = tvShowViewModel.getTvShows()

        tvShowAdapter = TvShowAdapter(context, tvShows) {
            val intent = Intent(context, TvShowDetailActivity::class.java)
            intent.putExtra(EXTRA_TV_SHOW_TITLE, it.title)
            startActivity(intent)
        }

        tvShowRV.setHasFixedSize(true)
        tvShowRV.adapter = tvShowAdapter
        tvShowRV.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            context,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
            false
        )
    }
}