package meaning.morning

import android.content.Context

class MeaningStorage (context: Context) {
    private val meaningSharedPref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private var meaningEdit = meaningSharedPref.edit()

    fun saveGroup(groupName: String, memberCount: String, groupContent: String) {
        meaningEdit.putString("groupName", groupName)
        meaningEdit.putString("memberCount", memberCount)
        meaningEdit.putString("groupContent", groupContent)
        meaningEdit.apply()
    }

    companion object{
        private var instance: MeaningStorage? = null

        fun getInstance(context: Context)= instance ?: synchronized(this) {
            instance ?: MeaningStorage(context).apply {
                instance = this
            }
        }

        const val PREFERENCE_NAME = "meaningPref"
    }
}