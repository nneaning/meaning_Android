package meaning.morning.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import meaning.morning.R
import meaning.morning.databinding.FragmentOnBoardingStartMeaningBinding
import meaning.morning.presentation.home.MainActivity

class OnBoardingStartFragment : Fragment() {
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentOnBoardingStartMeaningBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_on_boarding_start_meaning,
            container,
            false
        )
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentOnBoardingStartMeaningBinding) {
        binding.onBoardingStartNextButton.setOnClickListener {
            putUserDataEvent()
        }
    }

    private fun putUserDataEvent() {
        onBoardingViewModel.requestPutUserData()
    }
}
