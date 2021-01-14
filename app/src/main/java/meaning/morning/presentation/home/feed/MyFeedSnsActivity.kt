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

import MyFeedListData
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.ActivityMyFeedSnsBinding
import meaning.morning.data.SnsFeedData
import meaning.morning.utils.BindFeedPictureEvent

class MyFeedSnsActivity : AppCompatActivity(), BindFeedPictureEvent {

    private lateinit var binding: ActivityMyFeedSnsBinding

    private var snsRecyclerviewFragment = SnsRecyclerviewFragment()

    override fun requestToFeedPictureData() {
        setPictureRcv()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()

        setTransaction()

        val intent = intent
        Log.d("log111",intent.getParcelableArrayListExtra<MyFeedListData>("myFeedList").toString())
        Log.d("log112",intent.getStringExtra("successDay").toString())

    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_feed_sns)
        binding.myFeedSnsActivity = this
    }

    private fun setTransaction() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_myFeedSns, snsRecyclerviewFragment)
        transaction.commit()
    }

    private fun setPictureRcv() {
        val feedList = intent?.getParcelableArrayListExtra<MyFeedListData>("myFeedList")
        Log.d("log333",feedList!![0].createdAt)

        Log.d("log221",intent.getParcelableArrayListExtra<MyFeedListData>("myFeedList").toString())
        Log.d("log222",intent.getStringExtra("successDay").toString())

        var myFeedSnsData = mutableListOf<SnsFeedData>()
        myFeedSnsData.apply {
            add(
                SnsFeedData(
                    R.drawable.myfeed_profile,
                    "이형준",
                    "5분전",
                    "매일 오전 5시 기상",
                    "나비보배따우",
                    R.drawable.image_16
                )
            )
            add(
                SnsFeedData(
                    R.drawable.myfeed_profile,
                    "박효송",
                    "5분전",
                    "매일 오전 5시 기상",
                    "송송송송솟ㅇㅇ",
                    R.drawable.image_16
                )
            )
            add(
                SnsFeedData(
                    R.drawable.myfeed_profile,
                    "양승완",
                    "5분전",
                    "매일 오전 5시 기상",
                    "맨유우승안돼",
                    R.drawable.image_16
                )
            )
        }
        snsRecyclerviewFragment.setAdapter(myFeedSnsData.toList())
    }

    fun backButton() {
        finish()
    }
}