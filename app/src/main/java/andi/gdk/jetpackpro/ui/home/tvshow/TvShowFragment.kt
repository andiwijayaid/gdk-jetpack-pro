package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.TvShowEntity
import andi.gdk.jetpackpro.ui.home.tvshow.adapter.TvShowAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tv_series.view.*

class TvShowFragment : androidx.fragment.app.Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter
    private var tvSeriesData: ArrayList<TvShowEntity> = arrayListOf()

    private val extraTvSeries = "EXTRA_TV_SERIES"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_tv_series, container, false)

        tvShowAdapter = TvShowAdapter(context, tvSeriesData) {
//            val intent = Intent(context, TvSeriesDetailActivity::class.java)
//            intent.putExtra(extraTvSeries, it)
//            startActivity(intent)
        }
        view.tvSeriesRV.adapter = tvShowAdapter
        view.tvSeriesRV.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)

        return view
    }
}