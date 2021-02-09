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
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.data.GroupFeedListData
import meaning.morning.data.MyFeedMainListData
import meaning.morning.data.MyFeedPictureData
import meaning.morning.databinding.ActivityGroupFeedBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.GroupResponseData
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
        val call : Call<BaseResponse<List<GroupResponseData>>> =
            MeaningService.getInstance().requestGroupFeed(MeaningStorage.getInstance(this).accessToken, 0)
        call.enqueueListener(
            onSuccess = {

                binding.tvMyfeed.text = MeaningStorage.getInstance(this).getGroupName()

                val groupFeedList = it.body()!!.data
                var groupFeedMainList = mutableListOf<GroupFeedListData>()
                var groupFeedPictureData = mutableListOf<MyFeedPictureData>()

                for(i in groupFeedList!!.indices){
                    groupFeedPictureData.apply {
                        add(
                            MyFeedPictureData(
                                groupFeedList[i].timeStampImageUrl
                            )
                        )
                    }
                }
                pictureRecyclerviewFragment.setGroupAdapter(
                    groupFeedPictureData.toList(),
                    groupFeedMainList.toList()
                )
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