package meaning.morning.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class HomeCardItemDecoreation (private val context: Context) : RecyclerView.ItemDecoration(){
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val horizontalGap = toPixel(20)
        outRect.right = horizontalGap / 2
    }

    @Px
    private fun toPixel(dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).roundToInt()
    }
}