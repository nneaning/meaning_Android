/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.presentation.group

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.ActivityCompleteGroupBinding

class CompleteGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCompleteGroupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_complete_group)
        binding.completeActivity = this
    }

    fun goToGroup() {
        finish()
        val intent = Intent(this, GroupBlankActivity::class.java)
        startActivity(intent)
    }
}
