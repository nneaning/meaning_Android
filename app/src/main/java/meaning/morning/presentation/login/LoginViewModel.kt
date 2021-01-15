/*
* Created by jinsu4755
* DESC:
*/

package meaning.morning.presentation.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import meaning.morning.MeaningStorage
import meaning.morning.data.entity.login.LoginData
import meaning.morning.network.request.LoginRequest
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.LoginResponse

class LoginViewModel(
    private val meaningStorage: MeaningStorage,
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isLoginClick = MutableLiveData(false)
    val isLoginClick: LiveData<Boolean>
        get() = _isLoginClick

    private val _isEnableLogin = MutableLiveData(false)
    val isEnableLogin: LiveData<Boolean>
        get() = _isEnableLogin

    private val _errorTextCaseId = MutableLiveData("")
    val errorTextCaseId: LiveData<String>
        get() = _errorTextCaseId

    private val _errorTextCasePassword = MutableLiveData("")
    val errorTextCasePassword: LiveData<String>
        get() = _errorTextCasePassword

    private val _loginTrigger = MutableLiveData(false)
    val loginTrigger: LiveData<Boolean>
        get() = _loginTrigger

    val inputIdForm = MutableLiveData("")
    val inputPasswordForm = MutableLiveData("")

    fun onLoginTrigger() {
        _isLoginClick.value = true
    }

    fun resetLoginTrigger() {
        _isLoginClick.value = false
    }

    fun enableLogin() {
        _isEnableLogin.value = true
    }

    fun disableLogin() {
        _isEnableLogin.value = false
    }

    fun setLoginEnable() {
        _loginTrigger.value = false
    }

    private fun isNullOrBlankIdInput() = inputIdForm.value.isNullOrBlank()
    private fun isNullOrBlankPasswordInput() = inputPasswordForm.value.isNullOrBlank()
    private fun isValidId() = !Patterns.EMAIL_ADDRESS
        .matcher(inputIdForm.value.toString())
        .matches()

    fun isCanLogin(): Boolean {
        idErrorEvent()
        passwordErrorEvent()
        return errorTextCaseId.value.isNullOrBlank() && errorTextCasePassword.value.isNullOrBlank()
    }

    private fun getUserLoginData(): LoginData = LoginData(
        inputIdForm.value.toString(),
        inputPasswordForm.value.toString()
    )

    private fun idErrorEvent() {
        if (isNullOrBlankIdInput()) {
            sendIdErrorMessage(NULL_OR_BLANK_ID)
            return
        }
        if (isValidId()) {
            sendIdErrorMessage(NON_VALID_ID)
            return
        }
        sendIdErrorMessage("")
    }

    private fun passwordErrorEvent() {
        if (isNullOrBlankPasswordInput()) {
            sendPasswordErrorMessage(NULL_OR_BLACK_PW)
            return
        }
        sendPasswordErrorMessage("")
    }

    private fun sendIdErrorMessage(idErrorMessage: String) {
        _errorTextCaseId.value = idErrorMessage
    }

    private fun sendPasswordErrorMessage(passwordErrorMessage: String) {
        _errorTextCasePassword.value = passwordErrorMessage
    }

    fun requestLogin() {
        _isLoading.value = true
        LoginRequest(getUserLoginData()).setEvent {
            onSuccessListener { successLoginEvent(it.data ?: return@onSuccessListener) }
            onErrorListener { failLoginEvent(it) }
        }.send()
    }

    private fun successLoginEvent(loginResponse: LoginResponse) {
        meaningStorage.apply {
            accessToken = loginResponse.accessToken
            refreshToken = loginResponse.refreshToken
            saveUserData(loginResponse.nickName, loginResponse.wakeUpTime)
        }
        _isLoading.value = false
        _loginTrigger.value = true
    }

    private fun failLoginEvent(baseResponse: BaseResponse<Unit>) {
        _isLoading.value = false
        when (baseResponse.status) {
            400 -> setFailLoginLabel(FAIL_LOGIN)
            500 -> setFailLoginLabel(SERVER_ERROR_TEXT)
        }
    }

    private fun setFailLoginLabel(label: String) {
        _errorTextCaseId.value = label
        _errorTextCasePassword.value = label
    }

    companion object {
        const val LOGIN_INIT_TOP_GUIDELINE = 0.17f
        const val LOGIN_INIT_BOTTOM_GUIDELINE = 0.68f
        const val LOGIN_ENABLE_TOP_GUIDELINE = 0.20f
        const val LOGIN_ENABLE_BOTTOM_GUIDELINE = 0.62f
        private const val NULL_OR_BLANK_ID = "아이디가 비어있습니다."
        private const val NULL_OR_BLACK_PW = "비밀번호가 비어있습니다."
        private const val NON_VALID_ID = "아이디가 이메일 형식이 아닙니다."
        private const val SERVER_ERROR_TEXT = "서버 오류입니다."
        private const val FAIL_LOGIN = "아이디/ 비밀번호를 확인해주세요"
    }
}
