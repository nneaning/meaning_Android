package meaning.morning.network.response

data class GroupDetailResponse(
    val groupDetail: GroupDetail
) {
    data class GroupDetail(
        val countMember: Int,
        val groupId: String,
        val groupName: String,
        val introduction: String,
        val maximumMemberNumber: Int
    )
}