package andi.gdk.jetpackpro.ui.home.movie.detail

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.MovieEntity
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment.Companion.EXTRA_MOVIE_TITLE
import andi.gdk.jetpackpro.utils.convertRatingToFloat
import andi.gdk.jetpackpro.utils.convertToCurrency
import andi.gdk.jetpackpro.utils.getDrawableId
import andi.gdk.jetpackpro.utils.isZero
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)

        val extras = intent.extras
        val movieTitle = extras?.getString(EXTRA_MOVIE_TITLE)
        movieDetailViewModel.setMovieTitle(movieTitle)

        initUI(movieDetailViewModel.getMovie())
    }

    private fun initUI(movie: MovieEntity) {
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

        titleTV.text = movie.title
        dateTV.text = movie.date
        overviewTV.text = getString(R.string.overview_format, movie.overview)
        ratingBar.rating = convertRatingToFloat(movie.rating)
        runtimeTV.text = movie.runtime.toString()

        if (isZero(movie.budget)) {
            budgetTV.text = getString(R.string.not_available_sign)
        } else {
            budgetTV.text = convertToCurrency(movie.budget)
        }

        if (isZero(movie.revenue)) {
            revenueTV.text = getString(R.string.not_available_sign)
        } else {
            revenueTV.text = convertToCurrency(movie.revenue)
        }

        Glide.with(this)
            .load(getDrawableId(applicationContext, movie.poster.toString()))
            .into(posterIV)
        Glide.with(this)
            .load(getDrawableId(applicationContext, movie.poster.toString()))
            .into(posterBackgroundIV)

        posterBackgroundIV.animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)

        watchBT.setOnClickListener {
            Toast.makeText(
                applicationContext,
                getString(R.string.watching),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
