package meaning.morning

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @BindingAdapter("app:image")
    @JvmStatic
    fun imageBinding(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}