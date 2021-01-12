/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created By: hyooosong
 * on 2021.01.05
 */

package meaning.morning.presentation.adapter.group

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.BindingDialog
import meaning.morning.data.RecommendGroupData
import meaning.morning.databinding.ItemGroupRecommendBinding

class RecommendGroupAdapter(context: Context) : RecyclerView.Adapter<RecommendGroupAdapter.VHolder>() {
    private val data: MutableList<RecommendGroupData> = mutableListOf()
    private val dialog = BindingDialog(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemGroupRecommendBinding
            .inflate(layoutInflater, parent, false)
            .let { VHolder(it) }
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.onBind(data[position])

        holder.sign.setOnClickListener {
            dialog.showDetailDialog()
        }
    }

    override fun getItemCount(): Int = data.size

    fun refreshData(list: List<RecommendGroupData>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    class VHolder(
        private val binding: ItemGroupRecommendBinding
        ) : RecyclerView.ViewHolder(binding.root) {
        val sign = binding.textviewSignGroup
        fun onBind(data: RecommendGroupData) {
            binding.imageviewGroupRecommend.setImageResource(data.groupImage)
            binding.recommendGroup = data
        }
    }
}