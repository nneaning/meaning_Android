/*
 * Created By: hyooosong
 * on 2021.01.05
 */

package meaning.morning.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.data.RecommendGroupData
import meaning.morning.databinding.ItemGroupRecommendBinding

class RecommendGroupAdapter : RecyclerView.Adapter<RecommendGroupAdapter.VHolder>() {
    private val data: MutableList<RecommendGroupData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemGroupRecommendBinding
            .inflate(layoutInflater, parent, false)
            .let { VHolder(it) }
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun refreshData(list: List<RecommendGroupData>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class VHolder(
        private val binding: ItemGroupRecommendBinding
        ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RecommendGroupData) {
            binding.imageviewGroupRecommend.setImageResource(data.groupImage)
            binding.recommendGroup = data
        }
    }
}