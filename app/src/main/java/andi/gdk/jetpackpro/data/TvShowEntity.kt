package andi.gdk.jetpackpro.data

data class TvShowEntity(
    val poster: String,
    val title: String,
    val date: String,
    val rating: Int,
    val runtime: Int,
    val numberOfEpisode: Int,
    val language: String,
    val overview: String
)