package andi.gdk.jetpackpro.utils

import java.text.DecimalFormat
import java.text.NumberFormat

fun normalizeRating(rating: Float?): Float {
    if (rating != null) {
        return rating.div(2)
    }
    return 0F
}

fun convertToCurrency(money: String?): String {
    val nf = NumberFormat.getCurrencyInstance()
    val pattern = (nf as DecimalFormat).toPattern()
    val newPattern = pattern.replace("\u00A4", "").trim { it <= ' ' }
    val newFormat = DecimalFormat(newPattern)
    return newFormat.format(money?.toLong())
}