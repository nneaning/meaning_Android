package meaning.morning.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.databinding.ItemListBinding
import meaning.morning.presentation.MyFeedPictureData

class MyFeedPictureAdapter (private val context: Context) : RecyclerView.Adapter<MyFeedPictureAdapter.MyFeedPictureViewHolder>(){

    var data = listOf<MyFeedPictureData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFeedPictureViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(context),parent,false)

        return MyFeedPictureViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyFeedPictureViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    inner class MyFeedPictureViewHolder(val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : MyFeedPictureData){
            binding.pictureList = data
        }
    }
}