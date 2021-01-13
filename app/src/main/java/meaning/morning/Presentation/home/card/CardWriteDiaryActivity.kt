package meaning.morning.presentation.home.card

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.databinding.ActivityCardWriteDiaryBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.MeaningService.Companion.meaningToken
import meaning.morning.network.request.CardDailyDiaryRequest
import meaning.morning.utils.customEnqueue


class CardWriteDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardWriteDiaryBinding
    val writeDairy = ObservableField<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_write_diary)
        binding.mission3 = this

        pressBtnDiaryUpload(binding.btnRegisterDiary)

        countTextNumDiary(binding.etDiary)

    }

    private fun pressBtnDiaryUpload(textView: TextView) {
        textView.setOnClickListener {
            if (checkNull()) {
                Toast.makeText(this, "내용을 입력해주세요", Toast.LENGTH_LONG).show()
            } else {
                connectDailyDiaryServer()
                MeaningStorage(this).saveMission3(1)
                finish()
            }
        }
    }

    private fun checkNull(): Boolean {
        return writeDairy.get().isNullOrEmpty()
    }

    private fun countTextNumDiary(etDiary: EditText) {
        etDiary.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length = etDiary.length()
                val convert = length.toString()
                binding.tvInputNum.setText(convert)
            }

        })
    }

    private fun connectDailyDiaryServer() {
        MeaningService.getInstance()
            .requestDailyDaiary(
                meaningToken,
                CardDailyDiaryRequest(writeDairy.get().toString())
            )
            .customEnqueue(
                Success = { response ->

                },
                Fail = {

                }
            )
    }

    fun backToHome() {
        finish()
    }

}