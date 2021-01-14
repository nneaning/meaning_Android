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

class RecommendGroupAdapter(context: Context) :
    RecyclerView.Adapter<RecommendGroupAdapter.VHolder>() {
    private val dialog = BindingDialog(context)
    private var imageGroupData = mutableListOf<RecommendGroupData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemGroupRecommendBinding
            .inflate(layoutInflater, parent, false)
            .let { VHolder(it) }

        return view
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.cardView.setOnClickListener {
            dialog.showDetailDialog(imageGroupData[position].groupId)
        }
        holder.onBind(imageGroupData[position])
    }

    override fun getItemCount(): Int = imageGroupData.size

    fun refreshData(list: List<RecommendGroupData>) {
        imageGroupData.addAll(list)
        notifyDataSetChanged()
    }

    class VHolder(
        private val binding: ItemGroupRecommendBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val cardView = binding.cardviewRecommendGroup
        fun onBind(data: RecommendGroupData) {
            binding.recommendGroup = data
        }
    }
}