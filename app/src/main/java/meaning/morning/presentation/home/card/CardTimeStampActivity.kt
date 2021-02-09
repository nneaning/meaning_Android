/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.presentation.home.card

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.databinding.ActivityCardTimeStampBinding
import meaning.morning.network.request.TimeStampPostRequest
import meaning.morning.presentation.home.MainActivity
import meaning.morning.presentation.login.LoginActivity
import meaning.morning.utils.showToast
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class CardTimeStampActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardTimeStampBinding
    val recognitionWakeup = ObservableField<String>()

    private val image: Uri? by lazy<Uri?> {
        intent.getParcelableExtra("image")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_time_stamp)
        binding.mission1 = this
        getButtonText()
        Log.d("jinsu4755", image?.toFile()?.name.toString())
        binding.ivMission1Picture.setImageURI(image)
        pressBtnPictureUpload(binding.btnUpload)
    }

    private fun getButtonText() {
        /*if (MeaningStorage.getInstance(this).getGroupId() == 0) {
            binding.btnUpload.text = "마이피드에 올리기"
            return
        }
        binding.btnUpload.text = "그룹에 사진 올리기"*/
    }

    private fun pressBtnPictureUpload(textView: TextView) {
        textView.setOnClickListener {
            if (checkNull()) {
                Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_LONG).show()
            } else {
                requestPostTimeStamp()
            }
        }
    }

    private fun checkNull(): Boolean {
        return recognitionWakeup.get().isNullOrEmpty()
    }

    private fun requestPostTimeStamp() {
        TimeStampPostRequest(
            dateTime = getFileName(),
            timeStampContent = getValidContent(),
            image = makeMultipartBody()
        ).setEvent {
            onSuccessListener { showHome() }
            onErrorListener { errorUpload(it.status) }
        }.send(MeaningStorage.getInstance(this).accessToken)
    }

    private fun makeMultipartBody(): MultipartBody.Part {
        val imageFile = image?.toFile()!!
        val requestBody = imageFile.asRequestBody("image/*".toMediaType())
        return MultipartBody.Part.createFormData("image", imageFile.name, requestBody)
    }

    private fun getFileName(): String {
        val name = image?.toFile()?.name.toString()
        return name.split(".")[0]
    }

    private fun getValidContent() = recognitionWakeup.get() ?: "게시물을 등록하고 나의 일상을 기록해보세요."

    private fun showHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    private fun errorUpload(status: Int) {
        when (status) {
            401 -> {
                showLogin()
                showToast("토큰이 만료되었습니다.")
            }
            500 -> showToast("서버 내부 오류가 있습니다.")
        }
    }

    private fun showLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    fun backToHome() {
        onBackPressed()
    }
}
