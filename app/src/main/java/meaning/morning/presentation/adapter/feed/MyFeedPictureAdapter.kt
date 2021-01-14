/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created by <LEE-HYUNGJUN>
 * DESC:
 */
package meaning.morning.presentation.adapter.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.databinding.FeedItemListBinding

import meaning.morning.data.MyFeedPictureData
import meaning.morning.presentation.adapter.home.HomeCardAdapter


class MyFeedPictureAdapter : RecyclerView.Adapter<MyFeedPictureAdapter.MyFeedPictureViewHolder>() {

    var data = mutableListOf<MyFeedPictureData>()

    private lateinit var itemClickListener : ItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFeedPictureViewHolder {
        val binding = FeedItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyFeedPictureViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyFeedPictureViewHolder, position: Int) {
        holder.onBind(data[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }


    fun submitData(list : List<MyFeedPictureData>){
        data.addAll(list)
        notifyDataSetChanged()
    }

    class MyFeedPictureViewHolder(val binding: FeedItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MyFeedPictureData) {
            binding.feedItemList = data
        }
    }

    interface ItemClickListener{
        fun onClick(view : View, position: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
}
