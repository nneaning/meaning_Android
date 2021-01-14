package meaning.morning.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import meaning.morning.network.response.MyFeedResponse


data class MyFeedMainListData(
    val feedList: List<myFeedMainList>

) {
    @Parcelize
    data class myFeedMainList(
    val createdAt: String,
    val id: Int,
    val timeStampContents: String,
    val timeStampImageUrl: String
    ):Parcelable
}

