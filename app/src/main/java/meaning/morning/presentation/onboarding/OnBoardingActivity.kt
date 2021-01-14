package meaning.morning.presentation.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.ActivityOnBoardingBinding
import meaning.morning.utils.replaceFragment

class OnBoardingActivity : AppCompatActivity() {
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityOnBoardingBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_on_boarding
        )
        binding.lifecycleOwner = this
        initView(binding)
    }

    private fun initView(binding: ActivityOnBoardingBinding) {
        replaceFragment(R.id.container_on_boarding, InputNickNameFragment())
        binding.onBoardingTopBarBackButton.setOnClickListener { onBackPressed() }
    }

    fun showBottomSheetModal() {
        BottomSheetModal().show(supportFragmentManager, "time")
    }
}
