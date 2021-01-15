package meaning.morning.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.databinding.ActivityOnBoardingBinding
import meaning.morning.presentation.home.MainActivity
import meaning.morning.utils.replaceFragment
import meaning.morning.utils.showToast

class OnBoardingActivity : AppCompatActivity() {
    private val onBoardingViewModel: OnBoardingViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = OnBoardingViewModel(
                MeaningStorage.getInstance(this@OnBoardingActivity)
            ) as T
        }
    }

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
        observeView()
    }

    private fun observeView() {
        onBoardingViewModel.putUserDataTrigger.observe(this) {
            if (it) {
                showHome()
                onBoardingViewModel.enablePutUserData()
            }
        }
        onBoardingViewModel.isErrorInServer.observe(this){
            if (it) {
                showToast("서버 에러가 있습니다.")
                onBoardingViewModel.resetServerErrorTrigger()
            }
        }
    }

    fun showBottomSheetModal() {
        BottomSheetModal().show(supportFragmentManager, "time")
    }

    private fun showHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
