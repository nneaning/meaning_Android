package meaning.morning.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import meaning.morning.R
import meaning.morning.databinding.FragmentOnBoardingInputTimeBinding
import meaning.morning.utils.replaceFragment
import meaning.morning.utils.replaceViewWithAnimation

class InputTimeFragment : Fragment() {
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentOnBoardingInputTimeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_on_boarding_input_time,
            container,
            false
        )
        binding.viewModel = onBoardingViewModel
        binding.lifecycleOwner = this
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentOnBoardingInputTimeBinding) {
        binding.textTimeResult.setOnClickListener {
            (requireActivity() as OnBoardingActivity).showBottomSheetModal()
        }
        binding.onBoardingTimeNextButton.setOnClickListener {
            (requireActivity() as OnBoardingActivity).replaceViewWithAnimation(
                R.id.container_on_boarding,
                OnBoardingStartFragment(),
                null
            )
        }
    }
}