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
import meaning.morning.data.GroupData
import meaning.morning.databinding.ItemGroupListBinding

class GroupAdapter(context: Context) : RecyclerView.Adapter<GroupAdapter.VHolder>() {
    private val data: MutableList<GroupData> = mutableListOf()
    private val dialog = BindingDialog(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemGroupListBinding
            .inflate(layoutInflater, parent, false)
            .let { VHolder(it) }
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.onBind(data[position])
        holder.groupName.setOnClickListener {
            dialog.showDetailDialog()
        }
    }

    override fun getItemCount(): Int = data.size

    fun refreshData(list: List<GroupData>) {
        data.addAll(list)
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