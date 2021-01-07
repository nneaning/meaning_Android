/*
 * Created By: hyooosong
 * on 2021.01.07
 */
package meaning.morning.presentation

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import meaning.morning.R
import meaning.morning.databinding.ActivityAddGroupBinding

class AddGroupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddGroupBinding
    val editTextNum = ObservableField<String>()
    val editTextName = ObservableField<String>()
    val editTextContent = ObservableField<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_group)
        binding.addgroupActivity = this

        changeLabelEvent(binding.edittextNum)
    }

    private fun changeLabelEvent(num: EditText) {
        num.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                changeLabelColor(binding.textviewRangeLimit)
            }
        })
    }

    private fun changeLabelColor(limitLabel: TextView) {
        if (!(editTextNum.get().isNullOrBlank()) && !checkRangeNum()) {
            limitLabel.setTextColor(
                Color.parseColor("#EB5757")
            )
            return
        }
        limitLabel.setTextColor(
            Color.parseColor("#828282")
        )
    }

    private fun checkRangeNum(): Boolean = editTextNum.get()?.toInt() in 2..100

    private fun validNum(): Boolean = checkRangeNum() && !(editTextNum.get().isNullOrBlank())

    private fun checkEditTextBlank(): Boolean {
        return (!(editTextName.get().isNullOrBlank()) &&
                !(editTextContent.get().isNullOrBlank()))
    }

    fun checkBlankEvent() {
        if (checkEditTextBlank() && !validNum()) {
            val intent = Intent(this, CompleteGroupActivity::class.java)
            startActivity(intent)
            return
        }
        if (checkEditTextBlank() && !validNum()) {
            Toast.makeText(this, "정확한 숫자를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(this, getString(R.string.blankText), Toast.LENGTH_SHORT).show()
    }

    fun backToGroupList() {
        finish()
    }
}

