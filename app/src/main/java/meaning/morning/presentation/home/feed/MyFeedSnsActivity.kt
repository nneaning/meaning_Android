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
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.data.MyFeedMainListData
import meaning.morning.data.SnsFeedData
import meaning.morning.databinding.ActivityMyFeedSnsBinding
import meaning.morning.network.MeaningService
import meaning.morning.utils.BindFeedPictureEvent
import meaning.morning.utils.DateParse

class MyFeedSnsActivity : AppCompatActivity(), BindFeedPictureEvent {

    private lateinit var binding: ActivityMyFeedSnsBinding

    private var snsRecyclerviewFragment = SnsRecyclerviewFragment()

    override fun requestToFeedPictureData() {

        val intent = intent
        val feedList = intent.getParcelableArrayListExtra<MyFeedMainListData>("myFeedList")
        val nickName = MeaningStorage.getInstance(this).nickName
        val firstName = MeaningStorage.getInstance(this).nickName?.slice(IntRange(0, 0))
        var myFeedSnsData = mutableListOf<SnsFeedData>()

        for (i in feedList!!.indices) {
            myFeedSnsData.apply {
                add(
                    SnsFeedData(
                        firstName!!,
                        nickName!!,
                       " 5분전",
                        "매일 오전 " + MeaningStorage.getInstance(baseContext).getWakeUpTime() + " 기상",
                        feedList[i].timeStampContents,
                        feedList[i].timeStampImageUrl
                    )
                )
            }
        }
        snsRecyclerviewFragment.setAdapter(myFeedSnsData.toList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()

        setTransaction()

        binding.tvSuccessDay.text =
            "오늘은 365일 중에 " + intent.getStringExtra("successDay") + "번째 의미있는 아침입니다."

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

    fun backButton() {
        finish()
    }

}