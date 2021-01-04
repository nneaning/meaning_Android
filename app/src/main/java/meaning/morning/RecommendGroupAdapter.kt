package meaning.morning

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.databinding.ItemGroupListBinding
import meaning.morning.databinding.ItemGroupRecommendBinding

class RecommendGroupAdapter(private val context: Context) : RecyclerView.Adapter<RecommendGroupAdapter.VHolder>() {
    var data = listOf<RecommendGroupData>()
    private lateinit var binding: ItemGroupRecommendBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        binding = ItemGroupRecommendBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return VHolder()
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class VHolder : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecommendGroupData) {
            binding.recommendGroup = data
        }
    }
}