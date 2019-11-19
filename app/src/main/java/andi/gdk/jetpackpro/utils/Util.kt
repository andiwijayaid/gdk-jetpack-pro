package andi.gdk.jetpackpro.utils

import android.content.Context
import java.text.DecimalFormat
import java.text.NumberFormat


fun getDrawableId(context: Context, drawableName: String?): Int? {
    return context.resources.getIdentifier(drawableName, "drawable", context.packageName)
}

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

fun isZero(money: String?): Boolean {
    return money == "0.00"
}

fun convertRatingToFloat(rating: Int?): Float {
    if (rating != null) {
        return rating * 5 / 100f
    }
    return 0F
}