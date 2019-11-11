package andi.gdk.jetpackpro.data

data class TvShow(
    private val poster: String,
    private val title: String,
    private val date: String,
    private val rating: Int,
    private val runtime: Int,
    private val numberOfEpisode: Int,
    private val language: String,
    private val overview: String
)