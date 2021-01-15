/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.network.request

import meaning.morning.network.api.UserService
import meaning.morning.network.response.LoginResponse

class RefreshTokenRequest {
    private var callback: RequestCallback<LoginResponse>? = null

    fun setEvent(block: RequestCallback<LoginResponse>.() -> Unit): RefreshTokenRequest {
        callback = RequestCallback<LoginResponse>().apply(block)
        return this
    }

    fun send(refreshToken: String?) {
        UserService.getInstance()
            .requestTokenRefresh(
                refreshToken = refreshToken
            ).enqueue(callback ?: return)
    }
}
