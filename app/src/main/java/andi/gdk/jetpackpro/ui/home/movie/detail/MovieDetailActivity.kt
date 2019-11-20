package andi.gdk.jetpackpro.ui.home.movie.detail

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment.Companion.EXTRA_MOVIE
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment.Companion.EXTRA_MOVIE_ID
import andi.gdk.jetpackpro.utils.convertToCurrency
import andi.gdk.jetpackpro.utils.isZero
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
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private var movieDetailViewModel: MovieDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieDetailViewModel = obtainViewModel(this)
        val extras = intent.extras
        val movieId = extras?.getInt(EXTRA_MOVIE_ID)
        movieDetailViewModel?.setId(movieId)

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
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val movie = intent.getParcelableExtra<MovieEntity>(EXTRA_MOVIE)
        titleTV.text = movie?.originalTitle
        dateTV.text = movie?.releaseDate
        if (movie?.overview != "") {
            overviewTV.text =
                String.format(resources.getString(R.string.overview_format), movie?.overview)
        }
        ratingBar.rating = normalizeRating(movie?.voteAverage)

        budgetTV.visibility = View.GONE
        revenueTV.visibility = View.GONE
        numberOfSeasonTV.visibility = View.INVISIBLE

        Glide.with(this)
            .load("${BuildConfig.IMAGE_URL}t/p/w500${movie?.posterPath}")
            .into(posterIV)
        Glide.with(this)
            .load("${BuildConfig.IMAGE_URL}t/p/original${movie?.backdropPath}")
            .into(posterBackgroundIV)

        posterBackgroundIV.animation = AnimationUtils.loadAnimation(this, R.anim.animaton_scale)

        movieDetailViewModel?.movie?.observe(this, Observer {
            if (it != null) {
                var mBudget = it.budget
                var mRevenue = it.revenue
                mBudget = convertToCurrency(mBudget)
                mRevenue = convertToCurrency(mRevenue)
                if (!isZero(it.budget)) {
                    budgetTV.text = mBudget
                }
                if (!isZero(it.revenue)) {
                    revenueTV.text = mRevenue
                }

                numberOfSeasonTV.text = it.runtime.toString()
                stopLoading()
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
        budgetTV.visibility = View.VISIBLE
        revenueTV.visibility = View.VISIBLE
        numberOfSeasonTV.visibility = View.VISIBLE

        budgetPB.visibility = View.GONE
        revenuePB.visibility = View.GONE
        numberOfSeasonPB.visibility = View.GONE
    }

    private fun obtainViewModel(activity: FragmentActivity?): MovieDetailViewModel? {
        val factory = ViewModelFactory.getInstance()
        return activity?.let {
            ViewModelProviders.of(it, factory).get(MovieDetailViewModel::class.java)
        }
    }
}
