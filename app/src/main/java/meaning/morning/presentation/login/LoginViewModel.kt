/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.presentation.login

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import meaning.morning.data.entity.login.LoginData
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
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

    fun getUserLoginData(): LoginData = LoginData(
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

    companion object {
        const val LOGIN_INIT_TOP_GUIDELINE = 0.17f
        const val LOGIN_INIT_BOTTOM_GUIDELINE = 0.68f
        const val LOGIN_ENABLE_TOP_GUIDELINE = 0.20f
        const val LOGIN_ENABLE_BOTTOM_GUIDELINE = 0.62f
        private const val NULL_OR_BLANK_ID = "아이디가 비어있습니다."
        private const val NULL_OR_BLACK_PW = "비밀번호가 비어있습니다."
        private const val NON_VALID_ID = "아이디가 이메일 형식이 아닙니다."

    }
}