package andi.gdk.jetpackpro.ui.home.favorite.movie.adapter

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.source.local.entity.MovieEntity
import andi.gdk.jetpackpro.utils.normalizeRating
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteMovieAdapter(private val context: Context?) :
    PagedListAdapter<MovieEntity, FavoriteMovieAdapter.FavoriteMovieViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        return FavoriteMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_favorite,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        if (context != null) {
            getItem(position)?.let { holder.bindItem(context, it) }
        }
    }

    class FavoriteMovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(context: Context, movie: MovieEntity) {
            Glide.with(context)
                .load("${BuildConfig.IMAGE_URL}t/p/w185${movie.posterPath}")
                .into(itemView.posterIV)
            itemView.itemParentCV.animation =
                AnimationUtils.loadAnimation(context, R.anim.animaton_slide_from_left)
            itemView.titleTV.text = movie.originalTitle
            itemView.ratingBar.rating = normalizeRating(movie.voteAverage)

        }
    }

}