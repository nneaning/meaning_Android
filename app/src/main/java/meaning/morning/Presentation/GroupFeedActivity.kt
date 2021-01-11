package meaning.morning.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.ActivityGroupFeedBinding
import meaning.morning.presentation.data.MyFeedPictureData
import meaning.morning.utils.BindFeedPictureEvent

class GroupFeedActivity : AppCompatActivity() , BindFeedPictureEvent {

    private lateinit var binding : ActivityGroupFeedBinding

    private var pictureRecyclerviewFragment = PictureRecyclerviewFragment()

    override fun requestToFeedPictureData() {
        setPictureRcv()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()

        setTransaction()
    }

    private fun setBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_feed)
        binding.groupFeedActivity = this
    }

    private fun setTransaction(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_groupFeed, pictureRecyclerviewFragment)
        transaction.commit()
    }

    private fun setPictureRcv(){
        var groupFeedPictureData = mutableListOf<MyFeedPictureData>()

        groupFeedPictureData.apply {
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
            add(MyFeedPictureData(R.drawable.image_16))
        }
        pictureRecyclerviewFragment.setAdapter(groupFeedPictureData.toList())

    }

    fun backButton(){
        finish()
    }


}