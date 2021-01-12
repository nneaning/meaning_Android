package meaning.morning.presentation.camera

import android.Manifest
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CameraViewModel : ViewModel() {

    var image: Bitmap? = null

    private val _currentTime = MutableLiveData<String>()
    val currentTime: LiveData<String>
        get() = _currentTime

    private val _currentDate = MutableLiveData<String>()
    val currentDate: LiveData<String>
        get() = _currentDate

    var isEnableTimer = false

    fun runCurrentTimer() = viewModelScope.launch() {
        while (isEnableTimer) {
            _currentTime.value = SimpleDateFormat(TIME_FORMAT, Locale.KOREA)
                .format(System.currentTimeMillis())
            _currentDate.value = SimpleDateFormat(DATE_FORMAT, Locale.KOREA)
                .format(System.currentTimeMillis())
            delay(10000)
        }
    }

    companion object {
        const val TIME_FORMAT = "hh : mm"
        const val DATE_FORMAT = "yyyy년 MM월 dd일 (EE)"
        const val REQUEST_CODE_PERMISSIONS = 10
    }
}