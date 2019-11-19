package andi.gdk.jetpackpro.ui.home.tvshow.detail

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowFragment.Companion.EXTRA_TV_SHOW
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowFragment.Companion.EXTRA_TV_SHOW_ID
import andi.gdk.jetpackpro.utils.normalizeRating
import andi.gdk.jetpackpro.viewmodel.ViewModelFactory
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tv_show_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    private var tvShowDetailViewModel: TvShowDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        tvShowDetailViewModel = obtainViewModel(this)
        val extras = intent.extras
        val tvShowId = extras?.getInt(EXTRA_TV_SHOW_ID)
        tvShowDetailViewModel?.setId(tvShowId)

        initUi()
    }

    private fun initUi() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        toolbar.setNavigationOnClickListener { finish() }

        val tvShow = intent.getParcelableExtra<TvShowEntity>(EXTRA_TV_SHOW)
        titleTV.text = tvShow?.title
        dateTV.text = tvShow?.firstAirDate
        ratingBar.rating = normalizeRating(tvShow?.rating)
        if (tvShow?.overview != "") {
            overviewTV.text =
                String.format(resources.getString(R.string.overview_format), tvShow?.overview)
        }

        numberOfSeasonTV.visibility = View.INVISIBLE
        numberOfEpsTV.visibility = View.INVISIBLE

        Glide.with(this)
            .load("${BuildConfig.IMAGE_URL}t/p/original${tvShow?.poster}")
            .into(posterIV)
        Glide.with(this)
            .load("${BuildConfig.IMAGE_URL}t/p/original${tvShow?.backdrop}")
            .into(posterBackgroundIV)

        posterBackgroundIV.animation = AnimationUtils.loadAnimation(this, R.anim.animaton_scale)

        tvShowDetailViewModel?.tvShow?.observe(this, Observer {
            if (it != null) {
                stopLoading()
                numberOfSeasonTV.text = it.numberOfSeasons?.toString()
                numberOfEpsTV.text = it.numberOfEpisodes?.toString()
            } else {
                stopLoading()
                Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.check_your_connection),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun stopLoading() {
        numberOfSeasonTV.visibility = View.VISIBLE
        numberOfEpsTV.visibility = View.VISIBLE

        numberOfSeasonPB.visibility = View.INVISIBLE
        numberOfEpsPB.visibility = View.INVISIBLE
    }

    private fun obtainViewModel(activity: FragmentActivity?): TvShowDetailViewModel? {
        val factory = ViewModelFactory.getInstance()
        return activity?.let {
            ViewModelProviders.of(it, factory).get(TvShowDetailViewModel::class.java)
        }
    }
}
