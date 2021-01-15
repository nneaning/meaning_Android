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
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.data.MyFeedMainListData
import meaning.morning.data.MyFeedPictureData
import meaning.morning.databinding.ActivityMyFeedMainBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.MyFeedResponse
import meaning.morning.utils.BindFeedPictureEvent
import meaning.morning.utils.enqueueListener
import retrofit2.Call
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

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
            MeaningService.getInstance()
                .requestMyFeed(MeaningStorage.getInstance(this).accessToken, 0)
        call.enqueueListener(
            onSuccess = {
                val myFeedList = it.body()!!.data!!.getMyPage
                val successDay = it.body()!!.data!!.successDays
                var myFeedMainList = mutableListOf<MyFeedMainListData>()
                var myFeedPictureData = mutableListOf<MyFeedPictureData>()
                binding.tvCountDay.text = "오늘은 365일 중에 " + successDay.toString() + "번째 의미있는 아침입니다"
                binding.tvNameInImage.text =
                    MeaningStorage.getInstance(this).nickName?.slice(IntRange(0, 0))
                binding.tvMyfeedName.text = MeaningStorage.getInstance(this).nickName
                binding.btnDate.text =
                    "매일 오전 " + MeaningStorage.getInstance(this).getWakeUpTime() + " 기상"

                for (i in myFeedList!!.indices) {
                    myFeedPictureData.apply {
                        add(
                            MyFeedPictureData(
                                myFeedList[i].timeStampImageUrl
                            )
                        )
                    }
                    myFeedMainList.apply {
                        add(
                            MyFeedMainListData(
                                myFeedList[i].createdAt,
                                myFeedList[i].id,
                                myFeedList[i].timeStampContents,
                                myFeedList[i].timeStampImageUrl
                            )
                        )
                    }
                }
                pictureRecyclerviewFragment.setAdapter(
                    myFeedPictureData.toList(),
                    myFeedMainList.toList(),
                    successDay.toString()
                )
            },
            onError = {
            }
        )
    }

    fun backButton() {
        finish()
    }

}
