package meaning.morning.network.response

data class GroupFeedResponse(
    val `data`: List<GroupResponseData>,
)

data class GroupResponseData(
    val user: GroupSettingResponse.User,
    val createdAt: String,
    val id: Int,
    val status: Int,
    val timeStampContents: String,
    val timeStampImageUrl: String,
)

data class GroupFeedUser(
    val id: Int,
    val nickName: String,
    val userName: String,
    val wakeUpTime: String,
)