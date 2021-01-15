/*
 * Created By: hyooosong
 * on 2021.01.08
 */
package meaning.morning.presentation.group

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.DialogGroupDetailBinding
import meaning.morning.databinding.DialogGroupRecyclerBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.MeaningService.Companion.meaningToken
import meaning.morning.network.request.GroupJoinApproveRequest
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.GroupDetailResponse
import meaning.morning.network.response.GroupJoinApproveResponse
import meaning.morning.utils.enqueueListener
import retrofit2.Call
import kotlin.properties.Delegates

class GroupDialog(private val context: Context) {
    private var setID by Delegates.notNull<Int>()
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
                setID = groupId
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
        remoteApproveDialog(recyclerDialogBinding, setID)
        closeDialog(recyclerDialogBinding.imageviewClose)
        closeDialog(recyclerDialogBinding.textviewOkBtn)
        approveDialog.show()
    }

    private fun remoteApproveDialog(binding: DialogGroupRecyclerBinding, groupid: Int) {
        val call: Call<GroupJoinApproveResponse> =
            MeaningService.getInstance().getApproveJoinGroup(
                meaningToken,
                GroupJoinApproveRequest(groupid)
            )
        call.enqueueListener(
            onSuccess = {
                if (it.body()!!.status == 201)
                    binding.textviewDialogLabel.text = "그룹 참가가 완료되었습니다."
            },
            onError = {
                Log.d("adad", it.errorBody()!!.toString())
                if(it.body()?.status != null) {
                    if (it.body()!!.status == 406) {
                        binding.textviewDialogLabel.text = "그룹 참가 기능 인원을 초과했어요."
                        return@enqueueListener
                    }
                    if (it.body()!!.status == 400) {
                        Log.d("adad", "dadadadadadad")
                        binding.textviewDialogLabel.text = "이미 함께 하고 있는 그룹이 있어요!"
                    }
                }
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