/*
 * Created by hyooosong
 * DESC:
 */

package meaning.morning.presentation.group

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.databinding.ActivityGroupBlankBinding
import meaning.morning.presentation.group.feed.GroupSettingActivity
import meaning.morning.presentation.home.MainActivity


class GroupBlankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityGroupBlankBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_group_blank)
        binding.blankgroupActivity = this

        binding.textviewGroupName.text =
            MeaningStorage.getInstance(this).getGroupName()
    }


    fun goToHome() {
        changeIntent(MainActivity())
        finish()
    }

    fun goToGroupSetting() {
        changeIntent(GroupSettingActivity())
    }

    fun backToGroupList() {
        finish()
    }

    private fun changeIntent(activity: AppCompatActivity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}