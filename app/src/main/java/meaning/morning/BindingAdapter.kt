package meaning.morning

import android.widget.ImageView
import android.widget.TextView
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

    @BindingAdapter("nick")
    @JvmStatic
    fun nickNameResult(textView: TextView, nickName: String?) {
        textView.text = nickName + textView.context.resources.getString(R.string.nick_name_result)
    }
}