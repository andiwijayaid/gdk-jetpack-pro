package andi.gdk.jetpackpro.ui.home.movie

import andi.gdk.jetpackpro.R
import andi.gdk.jetpackpro.data.MovieEntity
import andi.gdk.jetpackpro.ui.home.movie.adapter.MovieAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import java.util.*

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private var movies: ArrayList<MovieEntity> = arrayListOf()
    private lateinit var movieViewModel: MovieViewModel

    private val extraMovie = "EXTRA_MOVIE"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movies = movieViewModel.getMovies()

        movieAdapter = MovieAdapter(context, movies) {
            //            val intent = Intent(context, MovieDetailActivity::class.java)
//            intent.putExtra(extraMovie, it)
//            startActivity(intent)
        }

        movieRV.adapter = movieAdapter
        movieRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}