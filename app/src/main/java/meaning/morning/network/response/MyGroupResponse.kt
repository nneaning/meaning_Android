package meaning.morning.network.response

data class MyGroupResponse(
    val countMember: Int,
    val groupId: Int,
    val groupName: String,
    val introduction: String,
    val maximumMemberNumber: Int
)
