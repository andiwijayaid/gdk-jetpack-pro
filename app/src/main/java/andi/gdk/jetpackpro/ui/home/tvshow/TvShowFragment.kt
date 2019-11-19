package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.ui.home.tvshow.adapter.TvShowAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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

}