/*
 * Created by <LEE-HYUNGJUN>
 * DESC:
 */
package meaning.morning.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.databinding.SnsItemListBinding
import meaning.morning.presentation.data.SnsFeedData

class SnsFeedAdapter : RecyclerView.Adapter<SnsFeedAdapter.SnsFeedVH>() {

    var data = mutableListOf<SnsFeedData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnsFeedVH {
        val binding = SnsItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SnsFeedVH(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SnsFeedVH, position: Int) {
        holder.onBind(data[position])
    }

    fun submitData(list: List<SnsFeedData>) {
        data.addAll(list)
        notifyDataSetChanged()
    }


    class SnsFeedVH(val binding: SnsItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: SnsFeedData) {
            binding.snsItemList = data
        }
    }
}