/*
 * Created by jinsu4755
 * DESC: Login Feature
 */

package meaning.morning.presentation.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.ActivityLoginBinding
import meaning.morning.presentation.FragmentChangeActivity

class LoginActivity : FragmentChangeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

    }
}