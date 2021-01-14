package meaning.morning.utils

import android.content.Context
import android.widget.Toast
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <ResponseType> Call<ResponseType>.enqueueListener(
    onSuccess: (Response<ResponseType>) -> Unit,
    onError: (Response<ResponseType>) -> Unit = {},
    onFail: () -> Unit = {},
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            onFail()
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                onSuccess(response)
                return
            }
            onError(response)
        }
    })
}

fun showError(context: Context, error: ResponseBody?) {
    val e = error ?: return
    val ob = JSONObject(e.string())
    Toast.makeText(context, ob.getString("message"), Toast.LENGTH_SHORT).show()
}
