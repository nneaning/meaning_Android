/*
 * Created By: hyooosong
 * on 2021.01.08
 */
package meaning.morning

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import meaning.morning.databinding.DialogGroupDetailBinding
import meaning.morning.databinding.DialogGroupRecyclerBinding
import meaning.morning.databinding.FragmentGroupBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.MeaningService.Companion.meaningToken
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.GroupDetailResponse
import meaning.morning.utils.customEnqueue
import meaning.morning.utils.showError
import retrofit2.Call

class BindingDialog(private val context: Context) {
    private val binding: FragmentGroupBinding = DataBindingUtil
        .inflate(LayoutInflater.from(context), R.layout.fragment_group, null, false)

    private val dialogBinding: DialogGroupDetailBinding = DataBindingUtil
        .inflate(LayoutInflater.from(context), R.layout.dialog_group_detail, null, false)

    private val recyclerDialogBinding: DialogGroupRecyclerBinding = DataBindingUtil
        .inflate(LayoutInflater.from(context), R.layout.dialog_group_recycler, null, false)

    private var dialog = Dialog(context)
    private var approveDialog = Dialog(context)

    fun showDetailDialog(groupId: Int) {
        dialog.setCancelable(false)
        dialog.setContentView(dialogBinding.root)

        setGroupDetailData(dialogBinding, groupId)
        closeDialog(dialogBinding.imageviewClose)

        changeDialog(dialogBinding.textviewJoinBtn)

        dialog.show()
    }

    private fun setGroupDetailData(binding: DialogGroupDetailBinding, groupId: Int) {
        val call: Call<BaseResponse<GroupDetailResponse>> =
            MeaningService.getInstance().getGroupDetail(
                meaningToken, groupid = groupId
            )
        call.customEnqueue(
            onSuccess = {
                val groupDetailList = it.data!!.groupDetail
                binding.textviewDetailName.text = groupDetailList.groupName
                binding.textviewDetailContent.text = groupDetailList.introduction
                binding.textviewPeopleNum.text = groupDetailList.countMember.toString()
                binding.textviewPeopleLimit.text = groupDetailList.maximumMemberNumber.toString()
            },
            onError =
            {
                showError(context, it)
            }
        )
    }

    private fun closeDialog(closeBtn: View) {
        closeBtn.setOnClickListener {
            dialog.dismiss()
            approveDialog.dismiss()
        }
    }

    private fun showApproveDialog() {
        approveDialog.setCancelable(false)

        approveDialog.setContentView(recyclerDialogBinding.root)
        setLabelText(recyclerDialogBinding)
        closeDialog(recyclerDialogBinding.imageviewClose)
        closeDialog(recyclerDialogBinding.textviewOkBtn)
        approveDialog.show()
    }

    private fun hasMyGroup(): Boolean = binding.layoutMyGroup.visibility == View.VISIBLE

    private fun isOverLimit(): Boolean =
        dialogBinding.textviewPeopleNum.text.toString() == dialogBinding.textviewPeopleLimit.text.toString()

    private fun setLabelText(binding: DialogGroupRecyclerBinding) {
        lateinit var dialogLabel: String
        if (hasMyGroup()) {
            dialogLabel = "이미 함께 하고 있는 그룹이 있어요!"
            binding.textviewDialogLabel.text = dialogLabel
            return
        }
        if (isOverLimit()) {
            dialogLabel = "그룹 참가 기능 인원을 초과했어요."
            binding.textviewDialogLabel.text = dialogLabel
            return
        }
        dialogLabel = "그룹 참가가 완료되었습니다."
        binding.textviewDialogLabel.text = dialogLabel
    }

    private fun changeDialog(joinBtn: TextView) {
        joinBtn.setOnClickListener {
            dialog.dismiss()
            showApproveDialog()
        }
    }
}