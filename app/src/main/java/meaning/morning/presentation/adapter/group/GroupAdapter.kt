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
import meaning.morning.data.GroupData
import meaning.morning.databinding.ItemGroupListBinding

class GroupAdapter(context: Context) : RecyclerView.Adapter<GroupAdapter.VHolder>() {
    private val noImageGroupData: MutableList<GroupData> = mutableListOf()
    private val dialog = BindingDialog(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemGroupListBinding
            .inflate(layoutInflater, parent, false)
            .let { VHolder(it) }
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.onBind(noImageGroupData[position])
        holder.groupName.setOnClickListener {
           dialog.showDetailDialog(noImageGroupData[position].groupId)
        }
    }

    override fun getItemCount(): Int = noImageGroupData.size

    fun refreshData(list: List<GroupData>) {
        noImageGroupData.addAll(list)
        notifyDataSetChanged()
    }

    class VHolder(private val binding: ItemGroupListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val groupName = binding.textviewGroupName
        fun onBind(data: GroupData) {
            binding.group = data
        }
    }
}