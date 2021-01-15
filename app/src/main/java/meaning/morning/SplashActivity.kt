package meaning.morning

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.databinding.ActivitySplashBinding
import meaning.morning.network.request.RefreshTokenRequest
import meaning.morning.network.response.LoginResponse
import meaning.morning.presentation.home.MainActivity
import meaning.morning.presentation.login.LoginActivity
import meaning.morning.presentation.login.LoginInitFragment
import meaning.morning.utils.nextActivityAnimation
import meaning.morning.utils.replaceFragmentWithAnimation
import meaning.morning.utils.showToast

class SplashActivity : AppCompatActivity(), Animator.AnimatorListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.meaningSplash.addAnimatorListener(this)
    }

    override fun onAnimationStart(animation: Animator?) {
        replaceFragmentWithAnimation(R.id.container_splash_logo, LoginInitFragment(), null)
    }

    override fun onAnimationEnd(animation: Animator?) {
        if (isNotHaveToken()) {
            showLogin()
            return
        }
        hasTokenEvent()
    }

    private fun hasTokenEvent() {
        RefreshTokenRequest().setEvent {
            onSuccessListener { successTokenRefresh(it.data) }
            onErrorListener { errorTokenRefresh(it.status) }
        }.send(
            MeaningStorage.getInstance(this).refreshToken
        )
    }

    private fun successTokenRefresh(data: LoginResponse?) {
        MeaningStorage.getInstance(this).apply {
            accessToken = data?.accessToken
            refreshToken = data?.refreshToken
        }
        showHomeView()
    }

    private fun errorTokenRefresh(status: Int) {
        when (status) {
            400, 401 -> showLogin()
            500 -> showToast("서버 내부 에러입니다.")
        }
    }

    private fun showLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showHomeView() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isNotHaveToken() = MeaningStorage.getInstance(this)
        .accessToken.isNullOrBlank()

    override fun onAnimationCancel(animation: Animator?) = Unit

    override fun onAnimationRepeat(animation: Animator?) = Unit

    override fun finish() {
        super.finish()
        nextActivityAnimation()
    }
}
