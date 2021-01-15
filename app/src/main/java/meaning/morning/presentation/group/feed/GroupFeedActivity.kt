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
import meaning.morning.data.MyFeedPictureData
import meaning.morning.R
import meaning.morning.data.GroupFeedListData
import meaning.morning.databinding.ActivityGroupFeedBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.MeaningService.Companion.meaningToken
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
            MeaningService.getInstance().requestGroupFeed(
                meaningToken,0
            )
        call.enqueueListener(
            onSuccess = {
                val groupList = it.body()!!.data!!.data
                val groupFeedMainList = mutableListOf<GroupFeedListData>()
                val groupFeedPictureData = mutableListOf<MyFeedPictureData>()
                binding.tvShowNumber.text = "${MeaningStorage.getInstance(this).getGroupNumber()}명의 사람들이 함께 아침을 맞고 있어요!"

                for (i in groupList!!.indices){
                    groupFeedPictureData.apply {
                        add(
                            MyFeedPictureData(
                                groupList[i].timeStampImageUrl
                            )
                        )
                    }
                    groupFeedMainList.apply {
                        add(
                            GroupFeedListData(
                                groupList[i].createdAt,
                                groupList[i].id,
                                groupList[i].status,
                                groupList[i].timeStampContents,
                                groupList[i].timeStampImageUrl,
                                groupList[i].user[0].id,
                                groupList[i].user[0].nickName,
                                groupList[i].user[0].userName,
                                groupList[i].user[0].wakeUpTime
                            )
                        )
                    }
                }
                pictureRecyclerviewFragment.setGroupAdapter(
                    groupFeedPictureData.toList(),
                    groupFeedMainList.toList(),
                    )
            },
            onError = {
                Log.d(LOG_TAG,"error")
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