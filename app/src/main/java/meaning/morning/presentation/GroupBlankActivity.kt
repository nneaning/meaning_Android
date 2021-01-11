package meaning.morning.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.ActivityGroupBlankBinding

class GroupBlankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityGroupBlankBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_group_blank)
        binding.blankgroupActivity=this

        binding.textviewGroupName.text = "효송이를 좋아하는 모임"

    }
    fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun goToGroupSetting() {
        val intent = Intent(this, GroupSettingActivity::class.java)
        startActivity(intent)
    }
}