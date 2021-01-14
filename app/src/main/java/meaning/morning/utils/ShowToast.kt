package meaning.morning.utils

import android.content.Context

fun Context.showToast(msg: String) {
    MeaningToast(this, msg).showToast()
}