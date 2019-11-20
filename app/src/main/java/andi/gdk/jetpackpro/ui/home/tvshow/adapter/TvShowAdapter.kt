package andi.gdk.jetpackpro.ui.home.tvshow.adapter


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
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_tv_show.view.*


class TvShowAdapter(private val context: Context?) :
        androidx.recyclerview.widget.RecyclerView.Adapter<TvShowViewHolder>() {

    private val tvShows = arrayListOf<TvShowEntity>()

    fun setTvShows(tvShows: ArrayList<TvShowEntity>?) {
        if (tvShows == null) return
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    fun addTvShows(tvShows: ArrayList<TvShowEntity>?) {
        if (tvShows == null) return
        this.tvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TvShowViewHolder {
        return TvShowViewHolder(
                LayoutInflater.from(p0.context).inflate(
                    R.layout.item_tv_show,
                        p0,
                        false
                )
        )
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(p0: TvShowViewHolder, p1: Int) {
        if (context != null) {
            p0.bindItem(context, tvShows[p1])
        }
    }

}

class TvShowViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    fun bindItem(context: Context, tvShow: TvShowEntity) {
        itemView.itemParentCV.animation =
            AnimationUtils.loadAnimation(context, R.anim.animation_slide_from_right)
        itemView.titleTV.text = tvShow.originalName
        itemView.dateTV.text = tvShow.firstAirDate
        Glide.with(context)
            .load("${BuildConfig.IMAGE_URL}t/p/w185${tvShow.posterPath}")
            .into(itemView.posterIV)
        itemView.ratingBar.rating = normalizeRating(tvShow.voteAverage)

        itemView.setOnClickListener {
            val intent = Intent(context, TvShowDetailActivity::class.java)
            intent.putExtra(EXTRA_TV_SHOW_ID, tvShow.id)
            intent.putExtra(EXTRA_TV_SHOW, tvShow)
            context.startActivity(intent)
        }
    }
}