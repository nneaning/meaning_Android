package meaning.morning.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.R
import meaning.morning.databinding.FragmentOnBoardingStartMeaningBinding
import meaning.morning.presentation.home.MainActivity

class OnBoardingStartFragment : Fragment() {
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
            val intent = Intent(requireActivity(), MainActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }
}