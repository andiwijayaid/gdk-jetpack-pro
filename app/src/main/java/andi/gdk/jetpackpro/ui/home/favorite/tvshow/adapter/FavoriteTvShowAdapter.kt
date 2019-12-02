package andi.gdk.jetpackpro.ui.home.favorite.tvshow.adapter

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowFragment.Companion.EXTRA_TV_SHOW
import andi.gdk.jetpackpro.ui.home.tvshow.TvShowFragment.Companion.EXTRA_TV_SHOW_ID
import andi.gdk.jetpackpro.ui.home.tvshow.detail.TvShowDetailActivity
import andi.gdk.jetpackpro.utils.normalizeRating
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteTvShowAdapter(private val context: Context?) :
    PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.FavoriteTvSeriesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteTvSeriesViewHolder {
        return FavoriteTvSeriesViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_favorite,
                p0,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteTvSeriesViewHolder, position: Int) {
        if (context != null) {
            getItem(position)?.let { holder.bindItem(context, it) }
        }
    }

    class FavoriteTvSeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(context: Context, tvShow: TvShowEntity) {
            itemView.itemParentCV.animation =
                AnimationUtils.loadAnimation(context, R.anim.animaton_slide_from_left)
            itemView.titleTV.text = tvShow.originalName
            Glide.with(context)
                .load("${BuildConfig.IMAGE_URL}t/p/w185${tvShow.posterPath}")
                .into(itemView.posterIV)
            itemView.ratingBar.rating = normalizeRating(tvShow.voteAverage)
            itemView.setOnClickListener {
                val intent = Intent(context, TvShowDetailActivity::class.java)
                intent.putExtra(EXTRA_TV_SHOW, tvShow)
                intent.putExtra(EXTRA_TV_SHOW_ID, tvShow.id)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }
}