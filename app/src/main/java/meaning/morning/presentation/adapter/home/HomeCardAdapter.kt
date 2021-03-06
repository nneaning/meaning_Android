/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.presentation.adapter.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.databinding.HomeCardListItemBinding
import meaning.morning.data.HomeCardData

class HomeCardAdapter(val itemClick: (position: Int, cardItem: HomeCardData) -> Unit) :
    RecyclerView.Adapter<HomeCardAdapter.HomeCardVH>() {

    private val data: MutableList<HomeCardData> = mutableListOf()

    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCardVH {

        val binding = HomeCardListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeCardVH(binding)

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: HomeCardVH, position: Int) {
        holder.onBind(data[position])
        holder.itemView.setOnClickListener {
            itemClick(position, data[position])
            itemClickListener.onClick(it, position)
        }
    }

    fun submitData(list: List<HomeCardData>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    class HomeCardVH(val binding: HomeCardListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HomeCardData) {
            binding.ivCard.setImageResource(data.iv_card)
        }

    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

}
