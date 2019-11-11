package andi.gdk.jetpackpro.ui.home.tvshow.adapter


import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.TvShowEntity
import andi.gdk.jetpackpro.utils.convertRatingToFloat
import andi.gdk.jetpackpro.utils.getDrawableId
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_tv_series.view.*


class TvShowAdapter(private val context: Context?, private var tvSeries: ArrayList<TvShowEntity>, private val listener: (TvShowEntity) -> Unit) :
        androidx.recyclerview.widget.RecyclerView.Adapter<TvSeriesViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TvSeriesViewHolder {
        return TvSeriesViewHolder(
                LayoutInflater.from(p0.context).inflate(
                        R.layout.item_tv_series,
                        p0,
                        false
                )
        )
    }

    override fun getItemCount(): Int = tvSeries.size

    override fun onBindViewHolder(p0: TvSeriesViewHolder, p1: Int) {
        if (context != null) {
            p0.bindItem(context, tvSeries[p1], listener)
        }
    }

}

class TvSeriesViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    fun bindItem(context: Context, tvShow: TvShowEntity, listener: (TvShowEntity) -> Unit) {
        itemView.titleTV.text = tvShow.title
        itemView.dateTV.text = tvShow.date
        itemView.runtimeTV.text = context.getString(R.string.run_time_format, tvShow.runtime)
        Glide.with(context)
                .load(getDrawableId(context, tvShow.poster))
                .into(itemView.posterIV)
        itemView.ratingBar.rating = convertRatingToFloat(tvShow.rating)

        itemView.setOnClickListener {
            listener(tvShow)
        }
    }
}