/*
 * Created by jinsu4755
 * DESC: Login Feature
 */

package meaning.morning.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.ActivityLoginBinding
import meaning.morning.presentation.onboarding.OnBoardingActivity
import meaning.morning.utils.replaceFragment
import meaning.morning.utils.replaceFragmentWithAnimation

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        replaceFragment(R.id.container_login_view, LoginInitFragment())
        observeLoginEvent()
        binding.loginTopBarBackButton.setOnClickListener { onBackPressed() }
    }

    private fun observeLoginEvent() {
        loginViewModel.isLoginClick.observe(this) {
            eventLoginTrigger(it)
        }
    }

    private fun eventLoginTrigger(loginTrigger: Boolean) {
        if (loginTrigger) {
            loginViewModel.resetLoginTrigger()
            loginButtonClickEvent()
            return
        }
    }

    private fun loginButtonClickEvent() {
        if (loginViewModel.isEnableLogin.value == true) {
            onClickEnabledLoginButton()
            return
        }
        enableLoginView()
    }

    private fun enableLoginView() {
        binding.loginTopGuideline.setGuidelinePercent(LoginViewModel.LOGIN_ENABLE_TOP_GUIDELINE)
        binding.loginBottomGuideline.setGuidelinePercent(LoginViewModel.LOGIN_ENABLE_BOTTOM_GUIDELINE)
        replaceFragmentWithAnimation(R.id.container_login_view, LoginContentFragment(), null)
        loginViewModel.enableLogin()
    }

    private fun disableLoginView() {
        binding.loginTopGuideline.setGuidelinePercent(LoginViewModel.LOGIN_INIT_TOP_GUIDELINE)
        binding.loginBottomGuideline.setGuidelinePercent(LoginViewModel.LOGIN_INIT_BOTTOM_GUIDELINE)
        loginViewModel.disableLogin()
    }

    private fun onClickEnabledLoginButton() {
        if (loginViewModel.isCanLogin()) {
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        if (loginViewModel.isEnableLogin.value == true) {
            disableLoginView()
            super.onBackPressed()
            return
        }
        super.onBackPressed()
    }
}
