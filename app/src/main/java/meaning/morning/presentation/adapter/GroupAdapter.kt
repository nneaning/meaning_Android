package meaning.morning.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.data.GroupData
import meaning.morning.databinding.ItemGroupListBinding

class GroupAdapter (private val context: Context) : RecyclerView.Adapter<GroupAdapter.VHolder>() {
    var data = listOf<GroupData>()
    private lateinit var binding: ItemGroupListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        binding = ItemGroupListBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return VHolder()
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class VHolder : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: GroupData) {
            binding.group = data
        }
    }
}
