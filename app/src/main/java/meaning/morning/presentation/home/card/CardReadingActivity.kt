/*
 * Created by LEE-HYUNGJUN
 * DESC:
 */


package meaning.morning.presentation.home.card

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.databinding.ActivityCardReadingBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.request.CardBookReadingRequest
import meaning.morning.utils.customEnqueue

class CardReadingActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCardReadingBinding

    val bookTitle = ObservableField<String>()
    val bookContents = ObservableField<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_reading)
        binding.mission4 = this

        pressBtnReading(binding.btnRegister)
        countTextNumReading(binding.etBookContents)
    }

    private fun pressBtnReading(textView: TextView) {
        textView.setOnClickListener {
            if (checkNotNull()) {
                connectBookReadingServer()
                MeaningStorage(this).saveMission4(1)
                finish()
            } else {
                Toast.makeText(this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun connectBookReadingServer() {
        MeaningService.getInstance()
            .requestBookReading(
                MeaningStorage.getInstance(this).accessToken,
                CardBookReadingRequest(bookContents.get().toString(), bookTitle.get().toString())
            ).customEnqueue(
                Success = {

                },
                Fail = {

                }
            )
    }

    private fun checkNotNull(): Boolean {
        return (!(bookTitle.get().isNullOrEmpty()) && !((bookContents.get().isNullOrEmpty())))
    }

    private fun countTextNumReading(etBookContents: EditText) {
        etBookContents.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length = etBookContents.length()
                val convert = length.toString()
                binding.tvInputNum.setText(convert)
            }

        })
    }

    fun backToHome() {
        finish()
    }

}