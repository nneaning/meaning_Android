/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created by <LEE-HYUNGJUN>
 * DESC:
 */
package meaning.morning.presentation.home.feed

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.data.MyFeedPictureData
import meaning.morning.R
import meaning.morning.databinding.ActivityMyFeedMainBinding
import meaning.morning.utils.BindFeedPictureEvent

class MyFeedMainActivity : AppCompatActivity(), BindFeedPictureEvent {

    private lateinit var binding: ActivityMyFeedMainBinding

    private var pictureRecyclerviewFragment = PictureRecyclerviewFragment()

    override fun requestToFeedPictureData() {
        // 서버 통신 로직을 적어줌
        Log.d("bind", "bind")
        setPictureRcv()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()

        setTransaction()


    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_feed_main)
        binding.myFeedmainActivity = this
    }

    private fun setTransaction() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_MyFeedMain, pictureRecyclerviewFragment)
        transaction.commit()
    }

    private fun setPictureRcv() {
        var myFeedPictureData = mutableListOf<MyFeedPictureData>()
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
        pictureRecyclerviewFragment.setAdapter(myFeedPictureData.toList())
    }


    fun backButton() {
        finish()
    }

}
