package andi.gdk.jetpackpro.data

data class MovieEntity(
    var poster: String,
    var title: String,
    var date: String,
    var rating: Int,
    var runtime: Int,
    var budget: Long,
    var revenue: Long,
    var overview: String
)