/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.network.request

import meaning.morning.data.entity.login.LoginData
import meaning.morning.network.api.UserService
import meaning.morning.network.response.LoginResponse

class LoginRequest(
    private val loginData: LoginData,
) {
    private var callback: RequestCallback<LoginResponse>? = null

    fun setEvent(block: RequestCallback<LoginResponse>.() -> Unit): LoginRequest {
        callback = RequestCallback<LoginResponse>().apply(block)
        return this
    }

    fun send() {
        UserService.getInstance()
            .requestSignIn(loginData)
            .enqueue(callback ?: return)
    }
}
