/*
 * Created by LEE-HYUNGJUN
 * DESC:
 */


package meaning.morning.presentation.home.card

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.databinding.ActivityCardPromiseBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.CardTodayPromise
import meaning.morning.utils.customEnqueue
import retrofit2.Response

class CardPromiseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardPromiseBinding
    val textViewFamousSaying = ObservableField<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()

        connectDayPromiseServer()

        pressBtnRead(binding.btnRead)

    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_promise)
        binding.mission2 = this
    }

    private fun pressBtnRead(textView: TextView) {
        textView.setOnClickListener {
            MeaningStorage(this).saveMission2(1)
            finish()
        }
    }

    private fun connectDayPromiseServer() {
        MeaningService.getInstance()
            .requestDayPromise(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTcsIm5hbWUiOiLrsJXtmqjshqEiLCJpYXQiOjE2MTA0NjgyMTIsImV4cCI6MTYxMjI4MjYxMiwiaXNzIjoiU2VydmVyQmFkIn0.sVKcyYHYkEe3nq5xi36hQDLn1XWpxI6l_ermMBt3aYE"
            )
            .customEnqueue(
                Success = { response ->
                    responseDayPromiseSuccess(response)
                },
                Fail = {
                    Log.d("logFail", "fail")
                }
            )
    }

    private fun responseDayPromiseSuccess(response: Response<BaseResponse<CardTodayPromise>>) {
        textViewFamousSaying.set(response.body()?.data?.contents)
    }

    fun backToHome() {
        finish()
    }
}