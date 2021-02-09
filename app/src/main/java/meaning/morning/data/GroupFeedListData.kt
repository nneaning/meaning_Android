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
): Parcelable