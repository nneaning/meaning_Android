/*
 * Created By: hyooosong
 * on 2021.01.07
 */
package meaning.morning.presentation.group

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.databinding.ActivityAddGroupBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.MeaningService.Companion.meaningToken
import meaning.morning.network.request.GroupAddRequest
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.GroupAddResponse
import meaning.morning.utils.customEnqueue
import meaning.morning.utils.showToast
import meaning.morning.utils.textCheck
import retrofit2.Call

class AddGroupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddGroupBinding
    val groupMemberNum = ObservableField<String>()
    val groupName = ObservableField<String>()
    val groupContent = ObservableField<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_group)
        binding.addgroupActivity = this

        changeLabelEvent(binding.edittextNum)
    }

    private fun remoteAddGroup(){
        val call: Call<BaseResponse<GroupAddResponse>> =
            MeaningService.getInstance().addGroup(
                meaningToken, GroupAddRequest(groupName.get().toString(), groupMemberNum.get()!!.toInt(), groupContent.get().toString())
            )
        call.customEnqueue(
            onSuccess = {
                MeaningStorage.getInstance(this).saveGroupId(it.body()!!.data!!.groupId)
            },
            onError = {
            }
        )
    }
    private fun changeLabelEvent(num: EditText) {
        num.textCheck(
            observeTextChanged = {
            },
            labelColorChanged = {
                changeLabelColor(binding.textviewRangeLimit)
            })
    }

    private fun changeLabelColor(limitLabel: TextView) {
        if (!(groupMemberNum.get().isNullOrBlank()) && !checkRangeNum()) {
            limitLabel.setTextColor(
                Color.parseColor("#EB5757")
            )
            return
        }
        limitLabel.setTextColor(
            Color.parseColor("#828282")
        )
    }

    private fun checkRangeNum(): Boolean = groupMemberNum.get()?.toInt() in 2..100

    private fun validNum(): Boolean = checkRangeNum() && !(groupMemberNum.get().isNullOrBlank())

    private fun checkEditTextBlank(): Boolean {
        return (!(groupName.get().isNullOrBlank()) &&
                !(groupContent.get().isNullOrBlank()))
    }

    fun checkBlankEvent() {
        if (checkEditTextBlank() && validNum()) {
            remoteAddGroup()
            val intent = Intent(this, CompleteGroupActivity::class.java)
            startActivity(intent)
            saveAddGroupData(
                binding.edittextGroupName.text.toString(),
                binding.edittextNum.text.toString(),
                binding.edittextGroupContent.text.toString()
            )
            finish()
            return
        }
        if (checkEditTextBlank() && !validNum()) {
            showToast("정확한 숫자를 입력해주세요")
            return
        }
        showToast(getString(R.string.blankText))
    }

    fun backToGroupList() {
        finish()
    }

    private fun saveAddGroupData(addName: String, memberLimit: String, addContent: String) {
        MeaningStorage.getInstance(applicationContext).saveGroup(addName, memberLimit, addContent)
    }
}

