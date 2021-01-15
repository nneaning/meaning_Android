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
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.databinding.ActivityGroupFeedBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.GroupFeedResponse
import meaning.morning.presentation.home.feed.PictureRecyclerviewFragment
import meaning.morning.utils.BindFeedPictureEvent
import meaning.morning.utils.enqueueListener
import retrofit2.Call

class GroupFeedActivity : AppCompatActivity(), BindFeedPictureEvent {

    private lateinit var binding: ActivityGroupFeedBinding

    private var pictureRecyclerviewFragment = PictureRecyclerviewFragment()

    override fun requestToFeedPictureData() {
        connectGroupFeedServer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()

        setTransaction()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_feed)
        binding.groupFeedActivity = this
    }

    private fun setTransaction() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_groupFeed, pictureRecyclerviewFragment)
        transaction.commit()
    }
    //,groupid = MeaningStorage.getInstance(this).getGroupId()

    private fun connectGroupFeedServer(){
        val call : Call<BaseResponse<GroupFeedResponse>> =
            MeaningService.getInstance().requestGroupFeed(MeaningStorage.getInstance(this).accessToken, 0)
        call.enqueueListener(
            onSuccess = {

            },
            onError = {

            }
        )

    }

    fun backButton() {
        finish()
    }

    companion object {
        private const val LOG_TAG = "Group_Feed"
    }
}