package meaning.morning.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.textCheck(
    observeTextChanged: (CharSequence?) -> Unit,
    labelColorChanged: (CharSequence?) -> Unit
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            observeTextChanged(s)
        }

        override fun afterTextChanged(s: Editable?) {
            labelColorChanged(s)
        }
    })

}