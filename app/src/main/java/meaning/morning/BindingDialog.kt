/*
 * Created By: hyooosong
 * on 2021.01.08
 */
package meaning.morning

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import meaning.morning.data.GroupDetailData
import meaning.morning.databinding.DialogGroupDetailBinding

class BindingDialog(private val context: Context) {
    private lateinit var dialogBinding: DialogGroupDetailBinding
    private var dialog = Dialog(context)
    fun showDialog() {
        dialogBinding = DataBindingUtil
            .inflate(LayoutInflater.from(context), R.layout.dialog_group_detail, null, false)
        dialog.setCancelable(false)
        dialog.setContentView(dialogBinding.root)
        setGroupData(dialogBinding)
        closeDialog(dialogBinding.imageviewClose)
        dialog.show()
    }

    private fun setGroupData(binding: DialogGroupDetailBinding) {
        binding.dialogData = GroupDetailData(
            "송이 좋아하는 그룹",
            "취준생끼리취준생끼리취준생끼리 \n취준생끼리취준생끼리취준생끼리 \n취준생끼리취준생끼리취준생끼리",
            "2",
            "/5")
    }

    private fun closeDialog(closeBtn: ImageView) {
        closeBtn.setOnClickListener {
            dialog.dismiss()
        }
    }
}