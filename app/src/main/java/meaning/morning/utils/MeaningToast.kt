package meaning.morning.utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import meaning.morning.R

class MeaningToast(
    context: Context,
    message: String,
    duration: Int,
) {
    private var toast: Toast? = null

    init {
        toast = Toast.makeText(context, message, duration)
        val view = getToastView(context)
        view.findViewById<TextView>(R.id.toast_message).apply {
            text = message
        }
        toast?.let {
            it.setGravity(Gravity.CENTER, 0, 550)
            it.setView(view)
        }
    }

    private fun getToastView(context: Context) = LayoutInflater.from(context)
        .inflate(R.layout.toast_gray4_bd, null)

    fun showToast() {
        toast?.show()
    }
}