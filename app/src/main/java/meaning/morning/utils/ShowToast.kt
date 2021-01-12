package meaning.morning.utils

import android.content.Context
import android.widget.Toast


fun Context.showToast(msg:String) {
    MeaningToast(this, msg).showToast()
}