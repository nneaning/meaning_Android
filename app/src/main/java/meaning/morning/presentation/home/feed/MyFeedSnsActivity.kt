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
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.data.MyFeedMainListData
import meaning.morning.data.SnsFeedData
import meaning.morning.databinding.ActivityMyFeedSnsBinding
import meaning.morning.utils.BindFeedPictureEvent

class MyFeedSnsActivity : AppCompatActivity(), BindFeedPictureEvent {

    private lateinit var binding: ActivityMyFeedSnsBinding

    private var snsRecyclerviewFragment = SnsRecyclerviewFragment()

    override fun requestToFeedPictureData() {

        val intent = intent
        val feedList = intent.getParcelableArrayListExtra<MyFeedMainListData>("myFeedList")
        var myFeedSnsData = mutableListOf<SnsFeedData>()
        for (i in feedList!!.indices) {
            myFeedSnsData.apply {
                add(
                    SnsFeedData(
                        "이형준",
                        "5분전",
                        "매일 오전 5시 기상",
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