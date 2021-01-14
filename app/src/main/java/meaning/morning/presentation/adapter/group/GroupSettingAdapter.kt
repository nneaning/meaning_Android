/*
 * Created By: hyooosong
 * on 2021.01.10
 */
package meaning.morning.presentation.adapter.group

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.R
import meaning.morning.data.GroupMemberData
import meaning.morning.databinding.ItemGroupMemberBinding

class GroupSettingAdapter : RecyclerView.Adapter<GroupSettingAdapter.VHolder>() {
    private val groupSettingUser: MutableList<GroupMemberData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemGroupMemberBinding
            .inflate(layoutInflater, parent, false)
            .let { VHolder(it) }
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.onBind(groupSettingUser[position])

        if (position % 2 == 1)
            holder.changeBackColor()
    }

    override fun getItemCount(): Int = groupSettingUser.size

    fun refreshData(list: List<GroupMemberData>) {
        groupSettingUser.addAll(list)
        notifyDataSetChanged()
    }

    class VHolder(private val binding: ItemGroupMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(memberData: GroupMemberData) {
            binding.groupMember = memberData
        }

        fun changeBackColor() {
            binding.textviewLastname.setBackgroundResource(R.drawable.member_profile_lightblue_background)
            binding.textviewLastname.setTextColor(Color.parseColor("#17234D"))
        }
    }
}