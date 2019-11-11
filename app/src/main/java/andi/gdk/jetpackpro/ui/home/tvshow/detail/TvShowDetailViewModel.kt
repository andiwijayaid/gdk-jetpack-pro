package andi.gdk.jetpackpro.ui.home.tvshow.detail

import andi.gdk.jetpackpro.data.TvShowEntity
import andi.gdk.jetpackpro.utils.generateDummyTvShows
import androidx.lifecycle.ViewModel

class TvShowDetailViewModel : ViewModel() {

    private lateinit var tvShowEntity: TvShowEntity
    private var tvShowTitle: String? = null

    fun getTvShow(): TvShowEntity {
        for (tvShow in generateDummyTvShows()) {
            if (tvShow.title == tvShowTitle) {
                tvShowEntity = tvShow
            }
        }
        return tvShowEntity
    }

    fun setTvShowTitle(tvShowTitle: String?) {
        this.tvShowTitle = tvShowTitle
    }
}