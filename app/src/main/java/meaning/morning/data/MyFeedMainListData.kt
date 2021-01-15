package meaning.morning.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyFeedMainListData(
    val createdAt: String,
    val id: Int,
    val timeStampContents: String,
    val timeStampImageUrl: String
): Parcelable
