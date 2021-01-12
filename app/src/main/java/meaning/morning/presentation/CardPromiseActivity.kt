package meaning.morning.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import meaning.morning.R
import meaning.morning.databinding.ActivityCardPromiseBinding

class CardPromiseActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCardPromiseBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_promise)
        binding.mission2 = this

    }

    private fun pressBtnRead(textView: TextView) {
        textView.setOnClickListener {
            //버튼 클릭시
        }
    }

    fun backToHome() {
        finish()
    }
}