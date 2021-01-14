import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyFeedListData(
    val createdAt: String,
    val id: Int,
    val timeStampContents: String,
    val timeStampImageUrl: String
): Parcelable