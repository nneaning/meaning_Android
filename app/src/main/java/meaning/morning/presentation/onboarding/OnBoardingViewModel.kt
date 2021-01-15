package meaning.morning.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import meaning.morning.MeaningStorage
import meaning.morning.data.entity.onboard.UserData
import meaning.morning.network.request.PutUserDataRequest

class OnBoardingViewModel(private val meaningStorage: MeaningStorage) : ViewModel() {

    private val _putUserDataTrigger = MutableLiveData(false)
    val putUserDataTrigger: LiveData<Boolean>
        get() = _putUserDataTrigger

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _dateTimeText = MutableLiveData("오전 4시 00분")
    val dateTimeText: LiveData<String>
        get() = _dateTimeText

    private val _isErrorInServer = MutableLiveData(false)
    val isErrorInServer: LiveData<Boolean>
        get() = _isErrorInServer

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

    fun enablePutUserData() {
        _putUserDataTrigger.value = false
    }

    fun resetServerErrorTrigger() {
        _isErrorInServer.value = false
    }

    private fun getMinuteText(): String {
        if (getValidMinute() < 10) {
            return "0${getValidMinute()}"
        }
        return getValidMinute().toString()
    }

    private fun getHourText(): String {
        if (getValidHour() < 10) {
            return "0${getValidHour()}"
        }
        return getValidHour().toString()
    }

    private fun getUserData() = UserData(
        nickName = getValidNickName(),
        wakeUpTime = "${getHourText()}:${getMinuteText()}:00"
    )

    private fun getValidHour() = _hour.value
        ?: throw IllegalArgumentException("유효하지 않은 시간 값입니다.")

    private fun getValidMinute() = _minute.value
        ?: throw IllegalArgumentException("유효하지 않은 분 값입니다.")

    private fun getValidNickName() = inputNickName.value
        ?: throw IllegalArgumentException("닉네임이 비어있을 수 없습니다.")

    fun requestPutUserData() {
        _isLoading.value = true
        PutUserDataRequest(getUserData()).setEvent {
            onSuccessListener { successPutUserData() }
            onErrorListener { errorPutUserData(it.status) }
        }.send(getAccessToken())
    }

    private fun getAccessToken() = meaningStorage.accessToken
        ?: throw IllegalArgumentException("엑세스 토큰이 없습니다.")

    private fun successPutUserData() {
        _isLoading.value = false
        _putUserDataTrigger.value = true
    }

    private fun errorPutUserData(status: Int) {
        _isLoading.value = false
        when (status) {
            500 -> {
                _isErrorInServer.value = true
            }
        }
    }
}
