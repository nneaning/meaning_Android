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
import meaning.morning.data.GroupDetailData
import meaning.morning.databinding.DialogGroupDetailBinding
import meaning.morning.databinding.DialogGroupRecyclerBinding
import meaning.morning.databinding.FragmentGroupBinding

class BindingDialog(private val context: Context) {
    private val binding: FragmentGroupBinding = DataBindingUtil
        .inflate(LayoutInflater.from(context), R.layout.fragment_group, null, false)
    private var dialog = Dialog(context)
    private var childDialog = Dialog(context)
    var overLimit: Boolean = false

    fun showDialog() {
        val dialogBinding: DialogGroupDetailBinding = DataBindingUtil
            .inflate(LayoutInflater.from(context), R.layout.dialog_group_detail, null, false)
        dialog.setCancelable(false)
        dialog.setContentView(dialogBinding.root)

        setGroupData(dialogBinding)
        closeDialog(dialogBinding.imageviewClose)
        showApproveDialog(dialogBinding.textviewJoinBtn)

        dialog.show()
    }

    private fun setGroupData(binding: DialogGroupDetailBinding) {
        binding.dialogData = GroupDetailData(
            "송이 좋아하는 그룹",
            "취준생끼리취준생끼리취준생끼리 \n취준생끼리취준생끼리취준생끼리 \n취준생끼리취준생끼리취준생끼리",
            "5",
            "5"
        )
        // 서버 값 (Num, Limit) 비교 후 overlimit 처리 로직 필요
    }


    private fun closeDialog(closeBtn: View) {
        closeBtn.setOnClickListener {
            dialog.dismiss()
            childDialog.dismiss()
        }
    }

    private fun checkMyGroup(): Boolean = binding.layoutMyGroup.visibility == View.VISIBLE

    private fun showChildDialog() {
        val recyclerDialogBinding: DialogGroupRecyclerBinding = DataBindingUtil
            .inflate(LayoutInflater.from(context), R.layout.dialog_group_recycler, null, false)
        childDialog.setCancelable(false)

        childDialog.setContentView(recyclerDialogBinding.root)
        setTextDialog(recyclerDialogBinding)
        closeDialog(recyclerDialogBinding.imageviewClose)
        closeDialog(recyclerDialogBinding.textviewOkBtn)
        childDialog.show()
    }

    private fun setTextDialog(binding: DialogGroupRecyclerBinding) {
        var dialogLabel: String = if (!overLimit) {
            if (checkMyGroup()) {
                "이미 함께 하고 있는 그룹이 있어요!"
            } else
                "그룹 참가가 완료되었습니다."
        } else {
            "그룹 참가 가능 인원을 초과했어요."
        }

        binding.textviewDialogLabel.text = dialogLabel
    }

    private fun showApproveDialog(joinBtn: TextView) {
        joinBtn.setOnClickListener {
            dialog.dismiss()
            showChildDialog()
        }
    }
}