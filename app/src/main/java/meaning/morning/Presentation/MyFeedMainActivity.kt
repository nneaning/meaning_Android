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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        setPictureRcv()

    }

    private fun setBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_feed_main)
        binding.myFeedMainActivity = this
    }

    private fun setPictureRcv(){
        var myFeedPictureData = mutableListOf<MyFeedPictureData>()
        val myFeedPictureAdapter = MyFeedPictureAdapter(this)
        binding.rcvMyfeed.apply {
            layoutManager = GridLayoutManager(this@MyFeedMainActivity,3)
            adapter = myFeedPictureAdapter
        }

        myFeedPictureData.apply {
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
        }
        myFeedPictureAdapter.submitData(myFeedPictureData)
    }

}