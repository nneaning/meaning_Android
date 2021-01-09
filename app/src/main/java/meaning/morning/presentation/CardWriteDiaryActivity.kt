package meaning.morning.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import meaning.morning.R
import meaning.morning.databinding.ActivityCardWriteDiaryBinding

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

    private fun pressBtnDiaryUpload(textView: TextView){
        textView.setOnClickListener {
            if(writeDairy.get().isNullOrEmpty()){
                Toast.makeText(this,"내용을 입력해주세요",Toast.LENGTH_LONG).show()
            }
            else{

            }
        }
    }

    private fun countTextNumDiary(etDiary: EditText){
        etDiary.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length = etDiary.length()
                val convert = length.toString()
                binding.tvInputNum.setText(convert)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    fun backToHome(){
        finish()
    }

}