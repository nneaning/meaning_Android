package meaning.morning.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import meaning.morning.R
import meaning.morning.databinding.FragmentOnBoardingInputNickNameBinding
import meaning.morning.utils.replaceFragment
import meaning.morning.utils.replaceViewWithAnimation
import meaning.morning.utils.showToast

class InputNickNameFragment : Fragment() {
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentOnBoardingInputNickNameBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_on_boarding_input_nick_name,
            container,
            false
        )
        binding.viewModel = onBoardingViewModel
        binding.lifecycleOwner = this
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentOnBoardingInputNickNameBinding) {
        binding.onBoardingNickNextButton.setOnClickListener { nextButtonClickEvent() }
    }

    private fun nextButtonClickEvent() {
        if (onBoardingViewModel.inputNickName.value.isNullOrBlank()) {
            requireContext().showToast("닉네임을 입력해주세요!")
            return
        }
        changeInputTimeFragment()
    }

    private fun changeInputTimeFragment() {
        (requireActivity() as OnBoardingActivity).apply {
            showBottomSheetModal()
            replaceViewWithAnimation(R.id.container_on_boarding, InputTimeFragment(), null)
        }
    }
}
