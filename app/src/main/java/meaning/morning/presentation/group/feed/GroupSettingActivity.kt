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
import meaning.morning.utils.DateTimeParser
import meaning.morning.utils.enqueueListener
import meaning.morning.utils.wakeupTimeParser
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
                MeaningStorage.getInstance(this).accessToken,
                groupid = MeaningStorage.getInstance(this).getGroupId()
            )
        call.enqueueListener(
            onSuccess = {
                val settingGroupList = it.body()!!.data!!.group
                val settingUserList = it.body()!!.data!!.users
                val settingUserData = mutableListOf<GroupMemberData>()

                groupIdx = settingGroupList.groupId.toInt()
                binding.textviewGroupName.text = settingGroupList.groupName
                binding.textviewCreateDate.text = DateTimeParser(settingGroupList.createdAt)
                binding.textviewMemberNum.text = "${settingGroupList.currentMemberNumber}명"

                for (i in settingUserList.indices) {
                    settingUserData.apply {
                        add(
                            GroupMemberData(
                                settingUserList[i].userName.slice(IntRange(0, 0)),
                                settingUserList[i].userName,
                                wakeupTimeParser(settingUserList[i].wakeUpTime),
                                "${settingUserList[i].dayPassed}일째 진행 중"
                            )
                        )
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
