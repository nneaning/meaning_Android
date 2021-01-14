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

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import meaning.morning.data.MyFeedPictureData
import meaning.morning.R
import meaning.morning.data.MyFeedMainListData
import meaning.morning.databinding.ActivityMyFeedMainBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.MeaningService.Companion.meaningToken
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.CardTodayPromise
import meaning.morning.network.response.GroupListResponse
import meaning.morning.network.response.MyFeedResponse
import meaning.morning.presentation.adapter.feed.MyFeedPictureAdapter
import meaning.morning.utils.BindFeedPictureEvent
import meaning.morning.utils.customEnqueue
import meaning.morning.utils.showError
import okhttp3.Response
import retrofit2.Call

class MyFeedMainActivity : AppCompatActivity(), BindFeedPictureEvent {

    private lateinit var binding: ActivityMyFeedMainBinding

    private var pictureRecyclerviewFragment = PictureRecyclerviewFragment()

    override fun requestToFeedPictureData() {

        connectMyFeedServer()

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


    private fun connectMyFeedServer() {
        val call: Call<BaseResponse<MyFeedResponse>> =
            MeaningService.getInstance().requestMyFeed(meaningToken, 0)
        call.customEnqueue(
            onSuccess = {
                val myFeedList = it.data?.getMyPage
                val successDay = it.data?.successDays
//                var myFeedMainList = mutableListOf<MyFeedMainListData>()
                var myFeedPictureData = mutableListOf<MyFeedPictureData>()
                binding.tvCountDay.text = "오늘은 365일 중에 " + successDay.toString() + "번째 의미있는 아침입니다"
                for (i in myFeedList!!.indices) {
                    myFeedPictureData.apply {
                        add(
                            MyFeedPictureData(
                                myFeedList[i].timeStampImageUrl
                            )
                        )
                    }
//                    myFeedMainList.apply {
//                        add(
//                            MyFeedMainListData(
//
//                                myFeedList[i].createdAt,
//                                myFeedList[i].id,
//                                myFeedList[i].timeStampContents,
//                                myFeedList[i].timeStampImageUrl
//                            )
//                        )
//                    }
                }
                pictureRecyclerviewFragment.setAdapter(
                    myFeedPictureData.toList(),
                    successDay.toString()
                )
            },
            onError = {
                showError(this, it)
            }
        )
    }

    fun backButton() {
        finish()
    }

}
