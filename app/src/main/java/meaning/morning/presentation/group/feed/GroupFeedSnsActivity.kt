/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created by <LEE-HYUNGJUN>
 * DESC:
 */
package meaning.morning.presentation.group.feed

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.data.GroupFeedListData
import meaning.morning.databinding.ActivityGroupFeedSnsBinding
import meaning.morning.data.SnsFeedData
import meaning.morning.network.MeaningService
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.GroupFeedUser
import meaning.morning.network.response.GroupResponseData
import meaning.morning.presentation.home.feed.SnsRecyclerviewFragment
import meaning.morning.utils.BindFeedPictureEvent
import meaning.morning.utils.enqueueListener

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

        }
        snsRecyclerviewFragment.setAdapter(groupFeedSnsData.toList())
    }


    fun backButton() {
        finish()
    }
}
