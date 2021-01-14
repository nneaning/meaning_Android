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
import meaning.morning.network.request.GroupJoinApproveRequest
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.GroupDetailResponse
import meaning.morning.network.response.GroupJoinApproveResponse
import meaning.morning.utils.enqueueListener
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
        call.enqueueListener(
            onSuccess = {
                val groupDetailList = it.body()!!.data!!.groupDetail
                binding.textviewDetailName.text = groupDetailList.groupName
                binding.textviewDetailContent.text = groupDetailList.introduction
                binding.textviewPeopleNum.text = groupDetailList.countMember.toString()
                binding.textviewPeopleLimit.text = groupDetailList.maximumMemberNumber.toString()
            },
            onError = {

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
        remoteApproveDialog(recyclerDialogBinding)
        closeDialog(recyclerDialogBinding.imageviewClose)
        closeDialog(recyclerDialogBinding.textviewOkBtn)
        approveDialog.show()
    }


    private fun isOverLimit(): Boolean =
        dialogBinding.textviewPeopleNum.text.toString() == dialogBinding.textviewPeopleLimit.text.toString()

//    private fun setLabelText(binding: DialogGroupRecyclerBinding) {
//        lateinit var dialogLabel: String
//        if (hasMyGroup()) {
//            dialogLabel = "이미 함께 하고 있는 그룹이 있어요!"
//            binding.textviewDialogLabel.text = dialogLabel
//            return
//        }
//        if (isOverLimit()) {
//            dialogLabel = "그룹 참가 기능 인원을 초과했어요."
//            binding.textviewDialogLabel.text = dialogLabel
//            return
//        }
//        dialogLabel = "그룹 참가가 완료되었습니다."
//        binding.textviewDialogLabel.text = dialogLabel
//    }

    private fun remoteApproveDialog(binding: DialogGroupRecyclerBinding) {
        lateinit var dialogLabel: String
        val call: Call<GroupJoinApproveResponse> =
            MeaningService.getInstance().getApproveJoinGroup(
                meaningToken,
                GroupJoinApproveRequest(MeaningStorage.getInstance(context).getGroupId())
            )
        call.enqueueListener(
            onSuccess = {
//                Log.e("adad", "stats : " + it.status.toString())
//                if (it.status == 406) {
//                    dialogLabel = "이미 함께 하고 있는 그룹이 있어요!"
//                    binding.textviewDialogLabel.text = dialogLabel
//                    Log.e("adad",dialogLabel)
//                    return@customEnqueue
//                }
//                if (isOverLimit()) {
//                    dialogLabel = "그룹 참가 기능 인원을 초과했어요."
//                    binding.textviewDialogLabel.text = dialogLabel
//                    return@customEnqueue
//                }
//                binding.textviewDialogLabel.text = "그룹 참가가 완료되었습니다."
//                binding.textviewDialogLabel.text = dialogLabel
            },
            onError = {
//                showError(context, it)
            }
        )
    }


    private fun changeDialog(joinBtn: TextView) {
        joinBtn.setOnClickListener {
            dialog.dismiss()
            showApproveDialog()
        }
    }
}