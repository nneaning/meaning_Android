package meaning.morning

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.databinding.ActivitySplashBinding
import meaning.morning.presentation.login.LoginActivity
import meaning.morning.presentation.login.LoginInitFragment
import meaning.morning.utils.nextActivityAnimation
import meaning.morning.utils.replaceFragmentWithAnimation

class SplashActivity : AppCompatActivity(), Animator.AnimatorListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.meaningSplash.addAnimatorListener(this)
    }

    override fun onAnimationStart(animation: Animator?){
        replaceFragmentWithAnimation(R.id.container_splash_logo ,LoginInitFragment(),null)
    }

    override fun onAnimationEnd(animation: Animator?) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onAnimationCancel(animation: Animator?) = Unit

    override fun onAnimationRepeat(animation: Animator?) = Unit

    override fun finish() {
        super.finish()
        nextActivityAnimation()
    }
}