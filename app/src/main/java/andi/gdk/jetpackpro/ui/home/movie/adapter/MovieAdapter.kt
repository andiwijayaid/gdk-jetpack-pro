package andi.gdk.jetpackpro.ui.home.movie.adapter

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.MovieEntity
import andi.gdk.jetpackpro.utils.convertRatingToFloat
import andi.gdk.jetpackpro.utils.getDrawableId
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val context: Context?, private var movies: ArrayList<MovieEntity>, private val listener: (MovieEntity) -> Unit) :
        androidx.recyclerview.widget.RecyclerView.Adapter<MovieViewHolder>() {
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
            p0.bindItem(context, movies[p1], listener)
        }
    }

}

class MovieViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    fun bindItem(context: Context, movie: MovieEntity, listener: (MovieEntity) -> Unit) {
        itemView.titleTV.text = movie.title
        itemView.dateTV.text = movie.date
        itemView.runtimeTV.text = context.getString(R.string.run_time_format, movie.runtime)
        Glide.with(context)
                .load(getDrawableId(context, movie.poster))
                .into(itemView.posterIV)
        itemView.ratingBar.rating = convertRatingToFloat(movie.rating)

        itemView.setOnClickListener {
            listener(movie)
        }
    }
}