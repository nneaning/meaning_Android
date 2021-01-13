package meaning.morning.utils

import android.content.Context
import android.widget.Toast
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <ResponseType> Call<ResponseType>.customEnqueue(
    onSuccess: (ResponseType) -> Unit,
    onError: (ResponseBody?) -> Unit = {}
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            response.takeIf { it.isSuccessful }
                ?.body()
                ?.let {
                    onSuccess(it)
                } ?: onError(response.errorBody())
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
        }
    })
}

fun showError(context: Context, error: ResponseBody?) {
    val e = error ?: return
    val ob = JSONObject(e.string())
    Toast.makeText(context, ob.getString("message"), Toast.LENGTH_SHORT).show()
}
