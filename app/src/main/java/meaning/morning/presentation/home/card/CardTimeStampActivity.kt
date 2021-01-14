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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
           if(checkNull()){
               Toast.makeText(this,"내용을 입력하세요",Toast.LENGTH_LONG).show()
           }
            else{

           }
        }
    }
  
    private fun checkNull() : Boolean{
        return recognitionWakeup.get().isNullOrEmpty()
    }

    fun backToHome(){
        finish()
    }
}