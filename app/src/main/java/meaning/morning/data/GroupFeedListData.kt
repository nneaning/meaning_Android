package meaning.morning.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GroupFeedListData(
    val createdAt: String,
    val dataId: Int,
    val status: Int,
    val timeStampContents: String,
    val timeStampImageUrl: String,
    val userId: Int,
    val nickName: String,
    val userName: String,
    val wakeUpTime: String
): Parcelable