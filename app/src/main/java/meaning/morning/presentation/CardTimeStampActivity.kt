package meaning.morning.presentation

import android.database.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import meaning.morning.R
import meaning.morning.databinding.ActivityCardTimeStampBinding

class CardTimeStampActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCardTimeStampBinding
    val recognitionWakeup = ObservableField<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_card_time_stamp)
        binding.mission1 = this

        pressBtnPictureUpload(binding.btnUpload)

    }

    private fun pressBtnPictureUpload(textView: TextView){
        textView.setOnClickListener {
           if(recognitionWakeup.get().isNullOrEmpty()){
               Toast.makeText(this,"내용을 입력하세요",Toast.LENGTH_LONG).show()
           }
            else{

           }
        }
    }

    fun backToHome(){
        finish()
    }
}