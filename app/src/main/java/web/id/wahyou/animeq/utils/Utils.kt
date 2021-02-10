package web.id.wahyou.animeq.utils

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.text.DateFormat
import java.text.SimpleDateFormat

fun ViewGroup.sheetBehavior(): BottomSheetBehavior<*> {
    return BottomSheetBehavior.from(this)
}

@SuppressLint("SimpleDateFormat")
fun formatDate(date: String?): String{
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
    val dateParse = inputFormat.parse(date!!)
    return outputFormat.format(dateParse!!)
}