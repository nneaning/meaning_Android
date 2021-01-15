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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.databinding.ActivityLoginBinding
import meaning.morning.presentation.onboarding.OnBoardingActivity
import meaning.morning.utils.replaceFragment
import meaning.morning.utils.replaceFragmentWithAnimation

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                LoginViewModel(MeaningStorage.getInstance(this@LoginActivity)) as T
        }
    }
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
        loginViewModel.loginTrigger.observe(this) {
            if (loginViewModel.loginTrigger.value == true) {
                showOnBoarding()
                loginViewModel.setLoginEnable()
            }
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
            loginViewModel.requestLogin()
        }
    }

    private fun showOnBoarding() {
        val intent = Intent(this, OnBoardingActivity::class.java)
        startActivity(intent)
        finish()
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
