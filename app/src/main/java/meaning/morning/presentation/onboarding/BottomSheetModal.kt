package meaning.morning.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import meaning.morning.R
import meaning.morning.databinding.BottomSheetTimePickerBinding

class BottomSheetModal : BottomSheetDialogFragment() {
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: BottomSheetTimePickerBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_time_picker,
            container,
            false
        )
        initView(binding)
        return binding.root
    }

    private fun initView(binding: BottomSheetTimePickerBinding) {
        binding.timePickerOnBoarding.apply {
            hour = MIN_HOUR
            minute = MIN_MINUTE
        }
        binding.timePickerOnBoarding.setOnTimeChangedListener { _, hourOfDay, minute ->
            when {
                hourOfDay < MIN_HOUR -> {
                    setMinHour(binding)
                    setTimeText(MIN_HOUR, MIN_MINUTE)
                }
                hourOfDay > MAX_HOUR -> {
                    setMaxHour(binding)
                    setTimeText(MAX_HOUR, MAX_MINUTE)
                }
                else -> {
                    setTimeText(hourOfDay, minute)
                }
            }
        }
    }

    private fun setTimeText(hour: Int, minute: Int) {
        onBoardingViewModel.apply {
            setDateTime(hour, minute)
            setDateTimeToText()
        }
    }

    private fun setMinHour(binding: BottomSheetTimePickerBinding) {
        binding.timePickerOnBoarding.apply {
            hour = MIN_HOUR
            minute = MIN_MINUTE
        }
    }

    private fun setMaxHour(binding: BottomSheetTimePickerBinding) {
        binding.timePickerOnBoarding.apply {
            hour = MAX_HOUR
            minute = MAX_MINUTE
        }
    }

    companion object {
        const val MIN_HOUR = 4
        private const val MAX_HOUR = 8
        const val MIN_MINUTE = 0
        private const val MAX_MINUTE = 59
    }
}