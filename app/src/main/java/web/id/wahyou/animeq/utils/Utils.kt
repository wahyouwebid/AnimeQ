package web.id.wahyou.animeq.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import web.id.wahyou.animeq.databinding.BottomSheetBinding
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

fun errorDialogActivty(activity: Activity, context: Context) {
    val binding = BottomSheetBinding.inflate(activity.layoutInflater)
    val dialog = BottomSheetDialog(context)
    dialog.setContentView(binding.root)
    with(binding) {
        btnOk.setOnClickListener {
            dialog.dismiss()
        }
    }

    dialog.show()
}
