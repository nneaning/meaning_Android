package meaning.morning

import android.content.Context

class MeaningStorage(context: Context) {
    private val meaningSharedPref =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private var meaningEdit = meaningSharedPref.edit()

    var accessToken: String?
        get() = meaningSharedPref.getString(ACCESS_TOKEN, null)
        set(value) = meaningEdit.putString(ACCESS_TOKEN, value)
            .apply()

    var refreshToken: String?
        get() = meaningSharedPref.getString(REFRESH_TOKEN, null)
        set(value) = meaningEdit.putString(REFRESH_TOKEN, value)
            .apply()

    val nickName: String?
        get() = meaningSharedPref.getString(NICK_NAME, null)

    fun getGroupName(): String? {
        return meaningSharedPref.getString(GROUP_NAME, "")
    }
    fun saveGroupName(groupName: String) {
        meaningEdit.putString(GROUP_NAME, groupName)
        meaningEdit.apply()
    }

    fun saveGroupId(groupId: Int){
        meaningEdit.putInt(GROUP_ID, groupId).apply()
    }

    fun getGroupId(): Int{
        return meaningSharedPref.getInt(GROUP_ID, 0)
    }

    fun saveMission1(successMission1: Int) {
        meaningEdit.putInt("successMission1", successMission1)
        meaningEdit.apply()
    }

    fun saveMission2(successMission2: Int) {
        meaningEdit.putInt("successMission2", successMission2)
        meaningEdit.apply()
    }

    fun saveMission3(successMission3: Int) {
        meaningEdit.putInt("successMission3", successMission3)
        meaningEdit.apply()
    }

    fun saveMission4(successMission4: Int) {
        meaningEdit.putInt("successMission4", successMission4)
        meaningEdit.apply()
    }

    fun saveUserData(nickName: String?, wakeUpTime: String?) {
        meaningEdit.apply {
            putString(NICK_NAME, nickName)
            putString(TIME, wakeUpTime)
        }.apply()
    }

    fun getWakeUpTime() : String?{
        return meaningSharedPref.getString("wakeUpTime","")
    }

    companion object {
        private var instance: MeaningStorage? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: MeaningStorage(context).apply {
                instance = this
            }
        }

        const val PREFERENCE_NAME = "meaningPref"
        private const val ACCESS_TOKEN = "accessToken"
        private const val REFRESH_TOKEN = "refreshToken"
        private const val NICK_NAME = "nickName"
        private const val TIME = "wakeUpTime"
        private const val GROUP_ID = "groupId"
        private const val GROUP_NAME = "groupName"
    }
}
