package meaning.morning

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @BindingAdapter("app:image")
    @JvmStatic
    fun imageBinding(imageview: ImageView, imageUrl: String) {
        Glide.with(imageview.context)
            .load(imageUrl)
            .into(imageview)
    }
}