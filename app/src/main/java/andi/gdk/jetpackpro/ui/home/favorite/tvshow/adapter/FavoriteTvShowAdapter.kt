package andi.gdk.jetpackpro.ui.home.favorite.tvshow.adapter

import andi.gdk.jetpackpro.BuildConfig
import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.source.local.entity.TvShowEntity
import andi.gdk.jetpackpro.utils.normalizeRating
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteTvShowAdapter(
    private val context: Context?,
    private val listener: (TvShowEntity, Int) -> Unit
) :
    RecyclerView.Adapter<FavoriteTvSeriesViewHolder>() {

    private val mData = ArrayList<TvShowEntity>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteTvSeriesViewHolder {
        return FavoriteTvSeriesViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_favorite,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(p0: FavoriteTvSeriesViewHolder, p1: Int) {
        if (context != null) {
            p0.bindItem(context, mData[p1], listener)
        }
    }

    fun getTvSeries(): ArrayList<TvShowEntity> {
        return mData
    }

    fun setTvSeries(tvSeries: ArrayList<TvShowEntity>) {
        mData.clear()
        mData.addAll(tvSeries)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        mData.removeAt(position)
        notifyItemRemoved(position)
    }

}

class FavoriteTvSeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(context: Context, tvSeries: TvShowEntity, listener: (TvShowEntity, Int) -> Unit) {
        itemView.itemParentCV.animation =
            AnimationUtils.loadAnimation(context, R.anim.animaton_slide_from_left)
        itemView.titleTV.text = tvSeries.originalName
        Glide.with(context)
            .load("${BuildConfig.IMAGE_URL}t/p/w185${tvSeries.posterPath}")
            .into(itemView.posterIV)
        itemView.ratingBar.rating = normalizeRating(tvSeries.voteAverage)
        itemView.setOnClickListener {
            listener(tvSeries, layoutPosition)
        }
    }
}