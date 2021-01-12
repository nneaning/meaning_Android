
/*
 * Created By: hyooosong
 * on 2021.01.10
 */
package meaning.morning.presentation.group.feed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import meaning.morning.R
import meaning.morning.data.GroupMemberData
import meaning.morning.databinding.ActivityGroupSettingBinding
import meaning.morning.presentation.adapter.group.GroupSettingAdapter

class GroupSettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGroupSettingBinding
    private lateinit var groupSettingAdapter: GroupSettingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_setting)
        setGroupSettingAdapter()
        loadGroupMember()
    }

    private fun setGroupSettingAdapter() {
        groupSettingAdapter = GroupSettingAdapter()
        binding.rcvGroupMember.apply {
            adapter = groupSettingAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun loadGroupMember() {
        var memberData = mutableListOf<GroupMemberData>()

        memberData.apply {
            add(
                GroupMemberData(
                    "박효송",
                    "매일 8시 기상",
                    "30일째 진행중"
                )
            )
            add(
                GroupMemberData(
                    "박진수",
                    "매일 5시 기상",
                    "20일째 진행중"
                )
            )
            add(
                GroupMemberData(
                    "이형준",
                    "매일 6시 기상",
                    "104일째 진행중"
                )
            )
            add(
                GroupMemberData(
                    "미닝",
                    "매일 4시 기상",
                    "110일째 진행중"
                )
            )
            add(
                GroupMemberData(
                    "미닝",
                    "매일 4시 기상",
                    "110일째 진행중"
                )
            )
            add(
                GroupMemberData(
                    "미닝",
                    "매일 4시 기상",
                    "110일째 진행중"
                )
            )
            add(
                GroupMemberData(
                    "미닝",
                    "매일 4시 기상",
                    "110일째 진행중"
                )
            )
            add(
                GroupMemberData(
                    "미닝",
                    "매일 4시 기상",
                    "110일째 진행중"
                )
            )
        }
        groupSettingAdapter.refreshData(memberData)
    }
}