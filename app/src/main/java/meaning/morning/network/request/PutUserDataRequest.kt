/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.network.request

import meaning.morning.data.entity.onboard.UserData
import meaning.morning.network.api.UserService
import meaning.morning.network.response.LoginResponse

class PutUserDataRequest(
    private val userData: UserData,
){
    private var callback: RequestCallback<Unit>? = null

    fun setEvent(block: RequestCallback<Unit>.() -> Unit): PutUserDataRequest {
        callback = RequestCallback<Unit>().apply(block)
        return this
    }

    fun send(token: String) {
        UserService.getInstance()
            .requestPutUserData(
                token = token,
                body = userData
            ).enqueue(callback?: return)
    }
}
