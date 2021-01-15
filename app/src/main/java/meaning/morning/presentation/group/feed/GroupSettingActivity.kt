/*
 * Created By: hyooosong
 * on 2021.01.10
 */
package meaning.morning.presentation.group.feed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.data.GroupMemberData
import meaning.morning.databinding.ActivityGroupSettingBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.GroupSettingResponse
import meaning.morning.presentation.adapter.group.GroupSettingAdapter
import meaning.morning.utils.enqueueListener
import retrofit2.Call
import kotlin.properties.Delegates

class GroupSettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGroupSettingBinding
    private lateinit var groupSettingAdapter: GroupSettingAdapter
    var groupIdx by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_setting)
        setGroupSettingAdapter()
        loadGroupMember()
        binding.settingActivity = this
    }

    private fun setGroupSettingAdapter() {
        groupSettingAdapter = GroupSettingAdapter()
        binding.rcvGroupMember.apply {
            adapter = groupSettingAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun loadGroupMember() {
        val call: Call<BaseResponse<GroupSettingResponse>> =
            MeaningService.getInstance().getGroupSetting(
                MeaningStorage.getInstance(this).accessToken, groupid = MeaningStorage.getInstance(this).getGroupId()
            )
        call.enqueueListener(
            onSuccess = {
                val settingGroupList = it.body()!!.data!!.group
                val settingUserList = it.body()!!.data!!.users
                val settingUserData = mutableListOf<GroupMemberData>()

                groupIdx = settingGroupList.groupId.toInt()
                binding.textviewGroupName.text = settingGroupList.groupName
                binding.textviewCreateDate.text =
                    "${settingGroupList.createdAt.slice(IntRange(0, 3))}년 " +
                            "${settingGroupList.createdAt.slice(IntRange(5, 6))}월 " +
                            "${settingGroupList.createdAt.slice(IntRange(8, 9))}일"
                binding.textviewMemberNum.text = "${settingGroupList.currentMemberNumber}명"

                for (i in settingUserList.indices) {
                    settingUserData.apply {
                        if (settingUserList[i].wakeUpTime.slice(IntRange(3, 4)) != "00") {
                            add(
                                GroupMemberData(
                                    settingUserList[i].nickName.slice(IntRange(0, 0)),
                                    settingUserList[i].nickName,
                                    "매일 ${settingUserList[i].wakeUpTime.slice(IntRange(1, 1))}시" +
                                            " ${settingUserList[i].wakeUpTime.slice(IntRange(3, 4))}분 기상",
                                    "${settingUserList[i].dayPassed}일째 진행 중"
                                )
                            )
                        }else{
                            add(
                                GroupMemberData(
                                    settingUserList[i].userName.slice(IntRange(0, 0)),
                                    settingUserList[i].userName,
                                    "매일 ${settingUserList[i].wakeUpTime.slice(IntRange(1, 1))}시 기상",
                                    "${settingUserList[i].dayPassed}일째 진행 중"
                                )
                            )
                        }
                    }
                }
                groupSettingAdapter.refreshData(settingUserData)
            },
            onError = {
            }
        )
    }


    fun back() {
        finish()
    }
}
