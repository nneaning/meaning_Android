/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created by jinsu4755
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
import meaning.morning.R
import meaning.morning.databinding.ActivityCardReadingBinding

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
            if (!checkNull()) {

            } else {
                Toast.makeText(this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun checkNull() : Boolean{
        return (bookTitle.get().isNullOrBlank() && (bookContents.get().isNullOrBlank()))
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