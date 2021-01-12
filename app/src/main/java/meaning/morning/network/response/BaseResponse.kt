/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.network.response

data class BaseResponse<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T?
)
