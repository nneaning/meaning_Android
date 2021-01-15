/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.network.response

data class LoginResponse(
    val accessToken: String,
    val nickName: String?,
    val refreshToken: String,
    val wakeUpTime: String?,
)
