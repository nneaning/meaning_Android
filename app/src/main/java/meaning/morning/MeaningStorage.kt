package meaning.morning

import android.content.Context
import android.util.Log

class MeaningStorage (context: Context) {
    private val meaningSharedPref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private var meaningEdit = meaningSharedPref.edit()

    val userToken : String?
        get() = meaningSharedPref.getString("userToken",null)

    fun saveGroup(groupName: String, memberCount: String, groupContent: String) {
        meaningEdit.putString("groupName", groupName)
        meaningEdit.putString("memberCount", memberCount)
        meaningEdit.putString("groupContent", groupContent)
        meaningEdit.apply()
    }

    fun saveUserToken(token: String) {
        meaningEdit.putString("userToken", token)
        meaningEdit.apply()
    }

    fun saveMission1(successMission1 : Int){
        meaningEdit.putInt("successMission1",successMission1)
        meaningEdit.apply()
    }
    fun saveMission2(successMission2 : Int){
        meaningEdit.putInt("successMission2",successMission2)
        meaningEdit.apply()
    }
    fun saveMission3(successMission3 : Int){
        meaningEdit.putInt("successMission3",successMission3)
        meaningEdit.apply()
    }
    fun saveMission4(successMission4 : Int){
        meaningEdit.putInt("successMission4",successMission4)
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