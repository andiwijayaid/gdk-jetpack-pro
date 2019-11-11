package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.MovieEntity
import andi.gdk.jetpackpro.ui.home.movie.adapter.MovieAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.view.*
import java.util.*

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private var movies: ArrayList<MovieEntity> = arrayListOf()
    private val extraMovie = "EXTRA_MOVIE"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        movieAdapter = MovieAdapter(context, movies) {
//            val intent = Intent(context, MovieDetailActivity::class.java)
//            intent.putExtra(extraMovie, it)
//            startActivity(intent)
        }
        view.movieRV.adapter = movieAdapter
        view.movieRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return view
    }
}