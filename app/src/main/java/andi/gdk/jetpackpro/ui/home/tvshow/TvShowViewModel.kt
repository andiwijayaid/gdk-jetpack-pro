package andi.gdk.jetpackpro.ui.home.tvshow

import andi.gdk.jetpackpro.data.TvShowEntity
import andi.gdk.jetpackpro.utils.generateDummyTvShows
import androidx.lifecycle.ViewModel

class TvShowViewModel : ViewModel() {

    fun getTvShows(): ArrayList<TvShowEntity> {
        return generateDummyTvShows()
    }

}