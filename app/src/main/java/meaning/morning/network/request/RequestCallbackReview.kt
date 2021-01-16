/*
 * Created by jinsu4755
 * DESC: callback 객체 구현하는 Request Class 시도해봄 그냥 궁금해서
 */

package meaning.morning.network.request

import android.text.TextUtils
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import meaning.morning.network.response.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestCallbackReview<T> : Callback<BaseResponse<T>> {

    private var onSuccessListener: ((BaseResponse<T>) -> Unit)? = null
    private var onErrorListener: ((BaseResponse<Unit>) -> Unit)? = null
    private var onFailureListener: ((t: Throwable) -> Unit)? = null

    fun onSuccessListener(listener: ((BaseResponse<T>) -> Unit)) {
        this.onSuccessListener = listener
    }

    fun onErrorListener(listener: (BaseResponse<Unit>) -> Unit) {
        this.onErrorListener = listener
    }

    fun onFailureListener(listener: (t: Throwable) -> Unit) {
        this.onFailureListener = listener
    }

    override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
        if (response.isSuccessful) {
            onSuccessListener?.invoke(response.body() ?: return)
            return
        }
        val errorBody = response.errorBody()?.string() ?: return
        val errorResponse = createResponseErrorBody(errorBody)
        onErrorListener?.invoke(errorResponse)
    }

    private fun createResponseErrorBody(errorBody: String): BaseResponse<Unit> {
        val gson = GsonBuilder().create()
        val responseType = object : TypeToken<BaseResponse<T>>() {}.type
        return gson.fromJson(errorBody, responseType)
    }

    override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
        Log.d("jinsu4755", "${t.message} \n")
        Log.d("jinsu4755", "${t.localizedMessage} \n")
        Log.d("jinsu4755", TextUtils.join("\n", t.stackTrace))
        onFailureListener?.invoke(t)
    }
}

