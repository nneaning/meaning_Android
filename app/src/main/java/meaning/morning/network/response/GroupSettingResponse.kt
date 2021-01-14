package meaning.morning.network.response

data class GroupSettingResponse(
    val group: Group,
    val users: List<User>
) {
    data class Group(
        val currentMemberNumber: Int,
        val groupId: String,
        val groupName: String,
        val introduction: String,
        val createdAt: String,
        val maximumMemberNumber: Int
    )

    data class User(
        val dayPassed: Int,
        val id: Int,
        val nickName: String,
        val userName: String,
        val wakeUpTime: String
    )
}
