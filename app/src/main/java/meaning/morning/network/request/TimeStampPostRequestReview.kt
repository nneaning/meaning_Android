/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.network.request

import meaning.morning.network.MeaningService
import meaning.morning.network.response.TimeStampResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class TimeStampPostRequestReview(
    private val dateTime: String,
    private val timeStampContent: String,
    private val image: MultipartBody.Part,
) {
    private var callback: RequestCallbackReview<TimeStampResponse>? = null

    fun setEvent(block: RequestCallback<TimeStampResponse>.() -> Unit): TimeStampPostRequestReview {
        callback = RequestCallbackReview<TimeStampResponse>().apply(block)
        return this
    }

    fun send(token: String?) {
        MeaningService.getInstance()
            .requestPostTimestamp(
                token = token,
                params = createPartMap(),
                image = image
            ).enqueue(callback ?: return)
    }

    private fun createPartMap(): HashMap<String, RequestBody> {
        val partMap = HashMap<String, RequestBody>()
        partMap["dateTime"] = dateTime.toRequestBody(MEDIA_TYPE_TEXT)
        partMap["timeStampContents"] = timeStampContent.toRequestBody(MEDIA_TYPE_TEXT)
        return partMap
    }

    companion object {
        private val MEDIA_TYPE_TEXT = "text/plain".toMediaType()
    }
}
