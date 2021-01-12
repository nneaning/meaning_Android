package meaning.morning

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.databinding.ActivitySplashBinding
import meaning.morning.presentation.home.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.meaningSplash.playAnimation()
        loadSplash()
    }

    private fun loadSplash() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}