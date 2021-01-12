/*
 * Created by <LEE-HYUNGJUN>
 * DESC:
 */
package meaning.morning.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.Presentation.data.MyFeedPictureData
import meaning.morning.databinding.FeedItemListBinding


class MyFeedPictureAdapter : RecyclerView.Adapter<MyFeedPictureAdapter.MyFeedPictureViewHolder>() {

    var data = mutableListOf<MyFeedPictureData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFeedPictureViewHolder {
        val binding = FeedItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyFeedPictureViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyFeedPictureViewHolder, position: Int) {
        holder.onBind(data[position])
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
}
