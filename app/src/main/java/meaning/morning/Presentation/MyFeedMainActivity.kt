package meaning.morning.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import meaning.morning.R
import meaning.morning.databinding.ActivityMyFeedMainBinding
import meaning.morning.databinding.ItemListBinding
import meaning.morning.presentation.adapter.MyFeedPictureAdapter

class MyFeedMainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMyFeedMainBinding
    private lateinit var binding2 : ItemListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setRcv()

    }

    fun init(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_feed_main)
        binding.myFeedMainActivity = this
    }

    fun setRcv(){
        val myFeedPictureAdapter = MyFeedPictureAdapter(this)
        binding.rcvMyfeed.layoutManager = GridLayoutManager(this,3)
        binding.rcvMyfeed.adapter = myFeedPictureAdapter
        myFeedPictureAdapter.data = listOf(
           MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16),
            MyFeedPictureData(R.drawable.image_16)


        )
        myFeedPictureAdapter.notifyDataSetChanged()
    }

}