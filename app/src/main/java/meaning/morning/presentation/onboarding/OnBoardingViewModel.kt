package meaning.morning.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnBoardingViewModel : ViewModel() {
    private val _dateTimeText = MutableLiveData("오전 4시 00분")
    val dateTimeText: LiveData<String>
        get() = _dateTimeText

    private val _hour = MutableLiveData(BottomSheetModal.MIN_HOUR)
    private val _minute = MutableLiveData(BottomSheetModal.MIN_MINUTE)

    val inputNickName = MutableLiveData("")

    fun setDateTimeToText() {
        _dateTimeText.value = "오전 ${_hour.value}시 ${getMinuteText()}분"
    }

    fun setDateTime(hour: Int, minute: Int) {
        _hour.value = hour
        _minute.value = minute
    }

    private fun getMinuteText(): String {
        if (_minute.value!! < 10) {
            return "0${_minute.value!!}"
        }
        return _minute.value.toString()
    }
}
