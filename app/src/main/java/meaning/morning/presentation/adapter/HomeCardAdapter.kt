package meaning.morning.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.databinding.HomeCardListItemBinding
import meaning.morning.presentation.data.HomeCardData

class HomeCardAdapter(val context: Context) : RecyclerView.Adapter<HomeCardAdapter.HomeCardVH>(){

    private var data : MutableList<HomeCardData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCardVH {

        val binding = HomeCardListItemBinding
                .inflate(LayoutInflater.from(context),parent,false)

        return HomeCardVH(binding)

//        val layoutInflater = LayoutInflater.from(parent.context)
//        return HomeCardListItemBinding.inflate(layoutInflater,parent,false)
//                .let { HomeCardVH(it) }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: HomeCardVH, position: Int) {
        holder.onBind(data[position])
    }

    fun submitData(list: List<HomeCardData>){
        data.addAll(list)
        notifyDataSetChanged()
    }

    class HomeCardVH(val binding : HomeCardListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : HomeCardData){
            binding.ivCard.setImageResource(data.iv_card)
        }
    }
}