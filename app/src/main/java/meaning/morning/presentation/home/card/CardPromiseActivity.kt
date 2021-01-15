/*
 * Created by LEE-HYUNGJUN
 * DESC:
 */


package meaning.morning.presentation.home.card

import android.os.Bundle
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
                MeaningStorage.getInstance(this).accessToken
            )
            .customEnqueue(
                Success = { response ->
                    successDayPromiseResponse(response)
                },
                Fail = {

                }
            )
    }


    private fun successDayPromiseResponse(response: Response<BaseResponse<CardTodayPromise>>) {
        textViewFamousSaying.set(response.body()?.data?.contents)
    }

    fun backToHome() {
        finish()
    }
}