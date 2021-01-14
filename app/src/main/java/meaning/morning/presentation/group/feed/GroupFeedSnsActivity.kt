/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created by <LEE-HYUNGJUN>
 * DESC:
 */
package meaning.morning.presentation.group.feed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.ActivityGroupFeedSnsBinding
import meaning.morning.data.SnsFeedData
import meaning.morning.presentation.home.feed.SnsRecyclerviewFragment
import meaning.morning.utils.BindFeedPictureEvent

class GroupFeedSnsActivity : AppCompatActivity(), BindFeedPictureEvent {

    private var snsRecyclerviewFragment = SnsRecyclerviewFragment()

    private lateinit var binding: ActivityGroupFeedSnsBinding

    override fun requestToFeedPictureData() {
        setPictureRcv()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()

        setTransaction()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_feed_sns)
        binding.groupFeedSnsActivity = this
    }

    private fun setTransaction() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_groupFeedSns, snsRecyclerviewFragment)
        transaction.commit()
    }

    private fun setPictureRcv() {
        var groupFeedSnsData = mutableListOf<SnsFeedData>()
        groupFeedSnsData.apply {
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
                    "송송송송송송",
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
        snsRecyclerviewFragment.setAdapter(groupFeedSnsData.toList())
    }

    fun backButton() {
        finish()
    }
}
