package andi.gdk.jetpackpro.ui.home.movie.adapter

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment.Companion.EXTRA_MOVIE
import andi.gdk.jetpackpro.ui.home.movie.MovieFragment.Companion.EXTRA_MOVIE_ID
import andi.gdk.jetpackpro.ui.home.movie.detail.MovieDetailActivity
import andi.gdk.jetpackpro.utils.normalizeRating
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val context: Context?) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MovieViewHolder>() {

    private val movies = arrayListOf<MovieEntity>()

    fun setMovies(movies: ArrayList<MovieEntity>?) {
        if (movies == null) return
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun addMovies(movies: ArrayList<MovieEntity>?) {
        if (movies == null) return
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_movie,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(p0: MovieViewHolder, p1: Int) {
        if (context != null) {
            p0.bindItem(context, movies[p1])
        }
    }

}

class MovieViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    fun bindItem(context: Context, movie: MovieEntity) {
        itemView.itemParentCV.animation =
            AnimationUtils.loadAnimation(context, R.anim.animaton_slide_from_left)
        itemView.titleTV.text = movie.originalTitle
        itemView.dateTV.text = movie.releaseDate
        Glide.with(context)
            .load("${BuildConfig.IMAGE_URL}t/p/w185${movie.posterPath}")
            .into(itemView.posterIV)
        itemView.ratingBar.rating = normalizeRating(movie.voteAverage)

        itemView.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movie.id)
            intent.putExtra(EXTRA_MOVIE, movie)
            context.startActivity(intent)
        }
    }
}