package andi.gdk.jetpackpro.ui.home.movie.detail

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment.Companion.EXTRA_MOVIE
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment.Companion.EXTRA_MOVIE_ID
import andi.gdk.jetpackpro.utils.convertToCurrency
import andi.gdk.jetpackpro.utils.normalizeRating
import andi.gdk.jetpackpro.viewmodel.ViewModelFactory
import andi.gdk.jetpackpro.vo.Status
import android.os.Bundle
import android.util.Log
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
    private var movie: MovieEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieDetailViewModel = obtainViewModel(this)
        val extras = intent.extras
        val movieId = extras?.getInt(EXTRA_MOVIE_ID)
        movie = intent?.getParcelableExtra(EXTRA_MOVIE)
        if (movieId != null) {
            Log.d("B", "${movieId}vvvvv")
            movieDetailViewModel?.setId(movieId)
        } else {
            Log.d("A", "${movie?.id}aaaaa")
            movieDetailViewModel?.setId(movie?.id)
        }
        setMovie()

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

        titleTV.text = movie?.originalTitle
        dateTV.text = movie?.releaseDate
        if (movie?.overview != "") {
            overviewTV.text =
                String.format(resources.getString(R.string.overview_format), movie?.overview)
        }
        ratingBar.rating = normalizeRating(movie?.voteAverage)

        budgetTV.visibility = View.GONE
        revenueTV.visibility = View.GONE
        runTimeTV.visibility = View.INVISIBLE

        Glide.with(this)
            .load("${BuildConfig.IMAGE_URL}t/p/w500${movie?.posterPath}")
            .into(posterIV)
        Glide.with(this)
            .load("${BuildConfig.IMAGE_URL}t/p/original${movie?.backdropPath}")
            .into(posterBackgroundIV)

        posterBackgroundIV.animation = AnimationUtils.loadAnimation(this, R.anim.animaton_scale)

        checkFavoriteStatus()

        favoriteBT.setOnClickListener {
            movieDetailViewModel?.setFavorite(movie)
        }
    }

    private fun checkFavoriteStatus() {
        favoriteBT.isChecked = movie?.isFavorite!!
    }

    private fun setMovie() {
        movieDetailViewModel?.movie?.observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        budgetTV.visibility = View.GONE
                        revenueTV.visibility = View.GONE

                        budgetPB.visibility = View.VISIBLE
                        revenuePB.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        stopLoading()
                        budgetTV.text = convertToCurrency(it.data?.budget.toString())
                        revenueTV.text = convertToCurrency(it.data?.revenue.toString())
                        runTimeTV.text = it.data?.runtime.toString()
                    }
                    Status.ERROR -> {
                        stopLoading()
                        Toast.makeText(
                            this,
                            getString(R.string.check_your_connection),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun stopLoading() {
        budgetTV.visibility = View.VISIBLE
        revenueTV.visibility = View.VISIBLE
        runTimeTV.visibility = View.VISIBLE

        budgetPB.visibility = View.GONE
        revenuePB.visibility = View.GONE
        runTimePB.visibility = View.GONE
    }

    private fun obtainViewModel(activity: FragmentActivity?): MovieDetailViewModel? {
        val factory = ViewModelFactory.getInstance(application)
        return activity?.let {
            ViewModelProviders.of(it, factory).get(MovieDetailViewModel::class.java)
        }
    }
}
