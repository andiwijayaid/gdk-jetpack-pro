package andi.gdk.jetpackpro.ui.home.tvshow.detail

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowFragment.Companion.EXTRA_TV_SHOW_TITLE
import andi.gdk.jetpackpro.utils.getDrawableId
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tv_show_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var tvShowDetailViewModel: TvShowDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        tvShowDetailViewModel = ViewModelProviders.of(this).get(TvShowDetailViewModel::class.java)

        val extras = intent.extras
        val tvShowTitle = extras?.getString(EXTRA_TV_SHOW_TITLE)
//        tvShowDetailViewModel.setTvShowTitle(tvShowTitle)
//
//        initUi(tvShowDetailViewModel.getTvShow())
    }

    private fun initUi(tvShow: TvShowEntity) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        toolbar.setNavigationOnClickListener { finish() }

        titleTV.text = tvShow.title
//        dateTV.text = tvShow.date
//        ratingBar.rating = convertRatingToFloat(tvShow.rating)
//        overviewTV.text = getString(R.string.overview_format, tvShow.overview)
//        runtimeTV.text = tvShow.runtime.toString()
//        numberOfEpsTV.text = tvShow.numberOfEpisode.toString()

        Glide.with(this)
            .load(getDrawableId(applicationContext, tvShow.poster))
            .into(posterIV)
        Glide.with(this)
            .load(getDrawableId(applicationContext, tvShow.poster))
            .into(posterBackgroundIV)

        watchBT.setOnClickListener {
            Toast.makeText(
                applicationContext,
                getString(R.string.watching),
                Toast.LENGTH_LONG
            ).show()
        }

        posterBackgroundIV.animation = AnimationUtils.loadAnimation(this, R.anim.animaton_scale)
    }
}
